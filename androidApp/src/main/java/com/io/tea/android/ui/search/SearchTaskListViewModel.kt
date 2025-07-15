package com.io.tea.android.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.ui.search.model.SearchTeaListConverter
import com.io.tea.android.ui.search.state.MyRegionFilter
import com.io.tea.android.ui.search.state.MyRegionListUseCaseState
import com.io.tea.android.ui.search.state.SearchTeaListViewModelUiState
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.common.component.model.converter.MyTeaConverter
import com.io.tea.android.ui.search.model.SearchTeaListModel
import com.io.tea.android.ui.notice.detail.NoticeDetailDestination
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.ui.search.model.SearchTeaFilterModel
import com.io.tea.android.ui.search.model.SearchTeaSortModel
import com.io.tea.domain.usecase.FetchMyTeaListUseCase
import com.io.tea.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SearchTeaListViewModel(
    // TODO : DummyUseCase
    private val fetchMyTeaListUseCase: FetchMyTeaListUseCase,
) : ViewModel(), KoinComponent {

    private val _isSortStateFlow = MutableStateFlow(false)
    val isSortStateFlow: MutableStateFlow<Boolean> = _isSortStateFlow

    private val _isFilterStateFlow = MutableStateFlow(false)
    val isFilterStateFlow: MutableStateFlow<Boolean> = _isFilterStateFlow

    private val _isPayDeleteStateFlow = MutableStateFlow(false)
    val isPayDeleteStateFlow: MutableStateFlow<Boolean> = _isPayDeleteStateFlow

    private val myTeaListStateFlow: MutableStateFlow<List<SearchTeaListModel>> =
        MutableStateFlow(emptyList())
    private val searchTeaSortListStateFlow: MutableStateFlow<List<SearchTeaSortModel>> =
        MutableStateFlow(emptyList())
    private val myRegionFilterListStateFlow: MutableStateFlow<List<MyRegionFilter>> =
        MutableStateFlow(emptyList())
    private val searchTeaFilterListStateFlow: MutableStateFlow<Map<String, List<SearchTeaFilterModel>>> =
        MutableStateFlow(emptyMap())

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _useCaseStateFlow: MutableStateFlow<MyRegionListUseCaseState> =
        MutableStateFlow(MyRegionListUseCaseState.Initial)
    val useCaseStateFlow: StateFlow<MyRegionListUseCaseState> =
        _useCaseStateFlow.asStateFlow()

    private val _uiStateFlow = MutableStateFlow(
        SearchTeaListViewModelUiState(
            myRegionList = listOf(),
            isLoad = true,
            isRefresh = false,
            isError = false,
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        fetchMyTeaList()
    }

    private fun fetchMyTeaList() {
        viewModelScope.launch {
            when (val result = fetchMyTeaListUseCase()) {
                is UseCaseResult.Success -> {
                    myTeaListStateFlow.value = result.data.map {
                        MyTeaConverter.convert(it)
                    }
                    searchTeaSortListStateFlow.value = listOf(
                        SearchTeaSortModel(1, "登録が新しい順", true),
                        SearchTeaSortModel(2, "登録が古い順"),
                        SearchTeaSortModel(3, "終了間近"),
                        SearchTeaSortModel(4, "残LOTの多い順"),
                        SearchTeaSortModel(5, "残LOTの少ない順"),
                    )
                    myRegionFilterListStateFlow.value = listOf(
                        MyRegionFilter(1, "One", "One Lot"),
                        MyRegionFilter(2, "Two", "Two Lot"),
                        MyRegionFilter(3, "Three", "Three Lot"),
                        MyRegionFilter(4, "Four", "Four Lot"),
                        MyRegionFilter(5, "Five", "Five Lot"),
                        MyRegionFilter(6, "Six", "Six Lot"),
                        MyRegionFilter(7, "Seven", "Seven Lot"),
                        MyRegionFilter(8, "Eight", "Eight Lot"),
                        MyRegionFilter(9, "Nine", "Nine Lot"),
                        MyRegionFilter(10, "Ten", "Ten Lot"),
                        MyRegionFilter(11, "Eleven", "Eleven Lot"),
                        MyRegionFilter(12, "Twelve", "Twelve Lot"),
                        MyRegionFilter(13, "Thirteen", "Thirteen Lot"),
                        MyRegionFilter(14, "Fourteen", "Fourteen Lot"),
                        MyRegionFilter(15, "Fifteen", "Fifteen Lot"),
                        MyRegionFilter(16, "Sixteen", "Sixteen Lot"),
                    )
                    searchTeaFilterListStateFlow.value =
                        SearchTeaListConverter.convert(myRegionFilterListStateFlow.value)
                    _isPayDeleteStateFlow.value = true
                    updateUseCaseStateFlow()
                    _uiStateFlow.update {
                        it.copy(
                            isLoad = false,
                        )
                    }
                    updateUseCaseStateFlow()
                }

                is UseCaseResult.Failure -> {
                    _uiStateFlow.update {
                        it.copy(
                            isError = true,
                        )
                    }
                }
            }
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onClickPayDelete() {
        _navigationStateFlow.value = NoticeDetailDestination(
            id = ""
        )
    }

    fun onSelectedSort(selectedItem: SearchTeaSortModel) {
        searchTeaSortListStateFlow.update { list ->
            list.map {
                if (it.id == selectedItem.id) {
                    it.copy(
                        isSelected = true
                    )
                } else {
                    it.copy(
                        isSelected = false
                    )
                }

            }
        }
        updateUseCaseStateFlow()
    }

    fun onCheckedFilter(checkedItem: SearchTeaFilterModel) {
        myRegionFilterListStateFlow.update { list ->
            list.map {
                if (it.id == checkedItem.id) {
                    it.copy(
                        isChecked = it.isChecked.not()
                    )
                } else {
                    it
                }
            }
        }
        val myRegionFilterModelMap =
            SearchTeaListConverter.convert(myRegionFilterListStateFlow.value)
        searchTeaFilterListStateFlow.update { myRegionFilterModelMap }
        updateUseCaseStateFlow()
    }

    fun onClickFilter() {
    }

    fun onClickSort(isSort: Boolean) {
        _isSortStateFlow.value = isSort.not()
        updateUseCaseStateFlow()
    }

    private fun updateUseCaseStateFlow() {
        _useCaseStateFlow.update {
            MyRegionListUseCaseState.Success(
                myTeaListStateFlow.value,
                searchTeaSortListStateFlow.value,
                searchTeaFilterListStateFlow.value
            )
        }
    }

    fun onClickCardItem(model: SearchTeaListModel) {
        _navigationStateFlow.value = MyTeaHomeDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
