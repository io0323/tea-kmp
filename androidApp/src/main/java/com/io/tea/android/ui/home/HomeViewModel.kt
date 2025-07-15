package com.io.tea.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.common.component.model.NotificationModel
import com.io.tea.android.ui.common.component.model.converter.BannerConverter
import com.io.tea.android.ui.common.component.model.converter.TeaConverter
import com.io.tea.android.ui.home.state.HomeDisplayState
import com.io.tea.android.ui.home.model.BannerModel
import com.io.tea.android.ui.home.model.HomeModel
import com.io.tea.android.ui.home.model.TeaModel
import com.io.tea.android.ui.home.state.HomeUiState
import com.io.tea.android.ui.home.state.HomeUseCaseState
import com.io.tea.android.ui.menu.DrawerMenuDestination
import com.io.tea.android.ui.notice.common.NoticeConverter
import com.io.tea.android.ui.notice.common.NoticeModel
import com.io.tea.android.ui.notice.detail.NoticeDetailDestination
import com.io.tea.android.ui.notice.list.NoticeListDestination
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.ui.region.list.MyTeaListDestination
import com.io.tea.android.ui.search.SearchTeaListDestination
import com.io.tea.domain.usecase.FetchBannerListUseCase
import com.io.tea.domain.usecase.FetchNoticeListUseCase
import com.io.tea.domain.usecase.FetchTeaListUseCase
import com.io.tea.domain.usecase.UseCaseResult
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(
    private val fetchBannerListUseCase: FetchBannerListUseCase,
    private val fetchTeaListUseCase: FetchTeaListUseCase,
    private val fetchNoticeListUseCase: FetchNoticeListUseCase,
) : ViewModel(), KoinComponent {

    private val bannerListStateFlow: MutableStateFlow<List<BannerModel>> =
        MutableStateFlow(emptyList())
    private val teaListStateFlow: MutableStateFlow<List<TeaModel>> =
        MutableStateFlow(emptyList())
    private val noticeListStateFlow: MutableStateFlow<List<NoticeModel>> =
        MutableStateFlow(emptyList())
    private val notificationStateFlow: MutableStateFlow<NotificationModel> =
        MutableStateFlow(NotificationModel.default)

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _uiStateFlow = MutableStateFlow(HomeUiState())
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    private val _displayStateFlow = MutableStateFlow(HomeDisplayState())
    internal val displayStateFlow = _displayStateFlow.asStateFlow()

    private val _useCaseStateFlow: MutableStateFlow<HomeUseCaseState> =
        MutableStateFlow(HomeUseCaseState.Initial)
    val useCaseStateFlow: StateFlow<HomeUseCaseState> =
        _useCaseStateFlow.asStateFlow()

    private val _modelStateFlow = MutableStateFlow(HomeModel.default)
    val modelStateFlow = _modelStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            fetchApis()
        }
    }

    private suspend fun fetchApis() = coroutineScope {
        val apiCalls = listOf(
            async { runCatching { fetchBannerList() } },
            async { runCatching { fetchMyTeaList() } },
            async { runCatching { fetchNoticeList() } }
        )
        try {
            val results = apiCalls.awaitAll()
            if (results.all { it.isSuccess }) {
                setHomeDisplayType(displayType = HomeDisplayState.DisplayState.SUCCESS)
            } else {
                setHomeDisplayType(displayType = HomeDisplayState.DisplayState.empty_ONE)
            }
            updateUiState(HomeUiState.State.SUCCESS)
            updateUseCaseState()

        } catch (e: Exception) {
            setHomeDisplayType(displayType = HomeDisplayState.DisplayState.empty_TWO)
        }
    }

    private fun fetchBannerList() {
        viewModelScope.launch {
            when (val result = fetchBannerListUseCase()) {
                is UseCaseResult.Success -> {
                    bannerListStateFlow.value = result.data.map {
                        BannerConverter.convert(it)
                    }
                }

                is UseCaseResult.Failure -> {
                    updateUiState(HomeUiState.State.ERROR)
                }
            }
        }
    }

    private fun fetchMyTeaList() {
        viewModelScope.launch {
            when (val result = fetchTeaListUseCase()) {
                is UseCaseResult.Success -> {
                    teaListStateFlow.value = result.data.map {
                        TeaConverter.convert(it)
                    }
                }

                is UseCaseResult.Failure -> {
                    updateUiState(HomeUiState.State.ERROR)

                }
            }
        }
    }

    private fun fetchNoticeList() {
        viewModelScope.launch {
            when (val result = fetchNoticeListUseCase()) {
                is UseCaseResult.Success -> {
                    noticeListStateFlow.value = result.data.map {
                        NoticeConverter.convert(it)
                    }.take(3)
                    notificationStateFlow.update {
                        NotificationModel(noticeList = noticeListStateFlow.value)
                    }
                }

                is UseCaseResult.Failure -> {
                    updateUiState(HomeUiState.State.ERROR)
                }
            }
        }
    }

    private fun setHomeDisplayType(displayType: HomeDisplayState.DisplayState) {
        _displayStateFlow.update {
            it.copy(
                displayType = displayType,
            )
        }
    }

    private fun updateUiState(state: HomeUiState.State) {
        _uiStateFlow.update {
            it.copy(
                state = state
            )
        }
    }

    private fun updateUseCaseState() {
        _useCaseStateFlow.update {
            HomeUseCaseState.Success(
                bannerListStateFlow.value,
                teaListStateFlow.value,
                notificationStateFlow.value
            )
        }
    }

    fun onClickSetting() {
        _navigationStateFlow.value = DrawerMenuDestination("1")
    }

    fun onClickBannerItem(model: BannerModel) {
        _navigationStateFlow.value = MyTeaHomeDestination
    }

    fun onClickTeaItem(model: TeaModel) {
        _navigationStateFlow.value = MyTeaHomeDestination
    }

    fun onClickMyRegion() {
        _navigationStateFlow.value = SearchTeaListDestination("1")
    }

    fun onClickTeaSearch() {
        _navigationStateFlow.value = MyTeaListDestination("i")
    }

    fun onClickNoticeDetail() {
        _navigationStateFlow.value = NoticeListDestination(
            id = ""
        )
    }

    fun onClickNoticeItem(noticeModel: NoticeModel) {
        _navigationStateFlow.value = NoticeDetailDestination(
            id = noticeModel.payName
        )
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
