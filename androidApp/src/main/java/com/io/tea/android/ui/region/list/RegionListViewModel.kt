package com.io.tea.android.ui.region.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.ui.delete.region.confirm.DeleteRegionConfirmDestination
import com.io.tea.android.ui.region.detail.MyTeaDetailDestination
import com.io.tea.android.ui.region.list.model.RegionFilterModel
import com.io.tea.android.ui.region.list.model.RegionListConverter
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.codeinput.CodeInputDestination
import com.io.tea.android.ui.region.list.model.RegionListModel
import com.io.tea.android.ui.region.list.state.RegionFilter
import com.io.tea.android.ui.region.list.state.RegionListLoadingState
import com.io.tea.domain.response.RegionDTO
import com.io.tea.domain.usecase.FetchNoAddedRegionUseCase
import com.io.tea.domain.usecase.FetchRegionListUseCase
import com.io.tea.domain.usecase.RegionListWithHeader
import com.io.tea.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegionListViewModel(
    application: Application,
    private val getRegionListUseCase: FetchRegionListUseCase,
    private val getNoAddedRegionUseCase: FetchNoAddedRegionUseCase,
) : AndroidViewModel(application) {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _regionListModelStateFlow = MutableStateFlow(RegionListModel.default)
    val regionListModelStateFlow: StateFlow<RegionListModel> = _regionListModelStateFlow

    private val _loadingState: MutableStateFlow<RegionListLoadingState> =
        MutableStateFlow(RegionListLoadingState.Initial)
    val loadingState: StateFlow<RegionListLoadingState> = _loadingState.asStateFlow()

    private var allRegionListWithHeader: List<RegionListWithHeader> = emptyList()

    private val regionFilterListStateFlow: MutableStateFlow<List<RegionFilter>> =
        MutableStateFlow(emptyList())

    init {
        initializeRegionList()
        fetchRegionFilterList()
    }

    private fun initializeRegionList() {
        viewModelScope.launch {
            _loadingState.value = RegionListLoadingState.Loading

            when (val result = getRegionListUseCase()) {
                is UseCaseResult.Success -> {
                    _loadingState.value = RegionListLoadingState.Success
                    allRegionListWithHeader = result.data

                    val size = allRegionListWithHeader.flatMap { it.regionList }.size
                    _regionListModelStateFlow.update {
                        it.copy(
                            regionList = result.data,
                            totalCount = size,
                            isEnableTeaAppDelete = true,
                        )
                    }
                }

                is UseCaseResult.Failure -> {
                    _loadingState.value = RegionListLoadingState.Failure(result.errorMessage)
                }
            }
        }
    }

    private fun fetchRegionFilterList() {
        regionFilterListStateFlow.value = listOf(
            RegionFilter(1, "One"),
            RegionFilter(2, "Two"),
            RegionFilter(3, "Three"),
            RegionFilter(4, "Four"),
            RegionFilter(5, "Five"),
            RegionFilter(6, "Six"),
            RegionFilter(7, "Seven"),
            RegionFilter(8, "Eight"),
            RegionFilter(9, "Nine"),
        )
        _regionListModelStateFlow.update {
            it.copy(
                filterList = RegionListConverter.convert(regionFilterListStateFlow.value),
            )
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onClickTeaAppDelete() {
        _navigationStateFlow.value = DeleteRegionConfirmDestination("")
    }

    fun onClickCode() {
        _navigationStateFlow.value = CodeInputDestination()
    }

    fun onChecked(isChecked: Boolean) {
        _regionListModelStateFlow.update {
            it.copy(
                isCheckedUnregistered = isChecked.not(),
            )
        }
        onClickNoAddedButton()
    }

    fun onCheckedFilter(checkedItem: RegionFilterModel) {
        regionFilterListStateFlow.value.map {
            if (it.id == checkedItem.id) {
                it.isChecked = checkedItem.isChecked.not()
            }
        }
        _regionListModelStateFlow.update {
            it.copy(
                filterList = RegionListConverter.convert(regionFilterListStateFlow.value),
            )
        }
        isFilter()
    }

    fun onFilterClearClick() {
        regionFilterListStateFlow.value.map {
            it.isChecked = false
        }
        _regionListModelStateFlow.update {
            it.copy(
                filterList = RegionListConverter.convert(regionFilterListStateFlow.value),
                isFilter = false
            )
        }
        isFilter()
    }

    fun onFilterClick() {
        // NOTE : 絞り込み
        isFilter()
    }

    fun onClickRegionCardItem(region: RegionDTO) {
        _navigationStateFlow.value = MyTeaDetailDestination(
            id = region.id.toString(),
        )
    }

    /**
     * 未追加ボタン押下イベント
     */
    private fun onClickNoAddedButton() {
        viewModelScope.launch {
            _loadingState.value = RegionListLoadingState.Loading
            val shouldFiltering = _regionListModelStateFlow.value.isCheckedUnregistered
            // TODO : 未登録のみを取得 + エリア毎groupByリストをセットします
            val noAddedRegionListWithHeader = if (shouldFiltering) {
                getNoAddedRegionUseCase(allRegionListWithHeader)
            } else {
                allRegionListWithHeader
            }
            val size = noAddedRegionListWithHeader.flatMap { it.regionList }.size
            _regionListModelStateFlow.update {
                it.copy(
                    regionList = noAddedRegionListWithHeader,
                    totalCount = size,
                    isEnableTeaAppDelete = true,
                )
            }
            _loadingState.value = RegionListLoadingState.Success
        }
    }

    private fun isFilter() {
        val isFilter = regionFilterListStateFlow.value.any { it.isChecked }
        _regionListModelStateFlow.update {
            it.copy(
                isFilter = isFilter
            )
        }
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
