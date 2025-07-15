package com.io.tea.android.ui.region.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.MainApplication
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.region.detail.model.RegionDetailConverter
import com.io.tea.android.ui.region.detail.state.AgreeState
import com.io.tea.android.ui.region.detail.state.RegionDetail
import com.io.tea.android.ui.region.detail.state.RegionDetailUseCaseState
import com.io.tea.android.ui.region.detail.state.RegionDetailsUiState
import com.io.tea.android.ui.webview.WebViewDestination
import com.io.tea.android.util.ImageUtil
import com.io.tea.domain.usecase.FetchRegionDetailsUseCase
import com.io.tea.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class RegionDetailViewModel(
    regionId: String,
    private val getRegionDetailsUseCase: FetchRegionDetailsUseCase
) : ViewModel(), KoinComponent {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _useCaseStateFlow: MutableStateFlow<RegionDetailUseCaseState> =
        MutableStateFlow(RegionDetailUseCaseState.Initial)
    val useCaseStateFlow: StateFlow<RegionDetailUseCaseState> = _useCaseStateFlow.asStateFlow()

    private val _agreeStateFlow = MutableStateFlow(initAgreeModel())
    val agreeStateFlow: MutableStateFlow<AgreeState> = _agreeStateFlow

    private val _uiStateFlow = MutableStateFlow(
        RegionDetailsUiState(
            isConfirmButtonEnabled = false
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        fetchRegionDetails("1")
    }

    private fun initAgreeModel(): AgreeState {
        return AgreeState(
            resText = R.string.region_detail__tos,
            linkedText = "利用規約",
            linkTextUrl = "https://www.google.com",
            isChecked = false
        )
    }

    private fun fetchRegionDetails(regionId: String) {
        viewModelScope.launch {
            _useCaseStateFlow.value = RegionDetailUseCaseState.Loading

            when (val result = getRegionDetailsUseCase(regionId = regionId.toLong())) {
                is UseCaseResult.Success -> {
                    // TODO : DummyData
                    val regionDetail = RegionDetail(
                        regionId = result.data.id,
                        imageUrl = ImageUtil.getDrawablePath(
                            context = MainApplication.appContext,
                            drawablePath = "${R.drawable.hangryangry}"
                        ),
                        description = result.data.description,
                        headerTitle = "Title",
                        campaignName = "Lot１",
                        campaignDetail = "Detail",
                        campaignPeriodStart = "20230930",
                        campaignPeriodEnd = "20240320",
                        pointExpirationStart = "20230930",
                        pointExpirationEnd = "20240320",
                        pointExpirationNote = "※但し、令和6年3月20日(水)23:59を最終期限とします。",
                        campaignSiteName = "Lot１Lot",
                        campaignSiteURL = "https://www.google.com",
                        tosTitle = "「Lot１」\nアプリ利用規約",
                        tosText = "https://www.google.com",
                    )
                    val regionDetailModel = RegionDetailConverter.convert(regionDetail)
                    _useCaseStateFlow.value = RegionDetailUseCaseState.Success(regionDetailModel)
                }

                is UseCaseResult.Failure -> {
                    _useCaseStateFlow.value = RegionDetailUseCaseState.Failure(result.errorMessage)
                }
            }
        }
    }

    private fun isConfirmButtonEnable() {
        _uiStateFlow.update {
            it.copy(
                isConfirmButtonEnabled = _agreeStateFlow.value.isChecked,
            )
        }
    }

    fun onCheckedTosAgree(isChecked: Boolean) {
        if ((_useCaseStateFlow.value is RegionDetailUseCaseState.Success).not()) return
        _agreeStateFlow.update {
            it.copy(
                isChecked = isChecked.not(),
            )
        }
        isConfirmButtonEnable()
    }

    fun onClickLink(url: String) {
        if ((_useCaseStateFlow.value is RegionDetailUseCaseState.Success).not()) return
        _navigationStateFlow.value = WebViewDestination(
            url = url,
            title = "giftpad"
        )
    }

    fun onClickAdd() {
        if ((_useCaseStateFlow.value is RegionDetailUseCaseState.Success).not()) return
        if (_uiStateFlow.value.isConfirmButtonEnabled) {
            // Dummy
            onPopBack()
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
