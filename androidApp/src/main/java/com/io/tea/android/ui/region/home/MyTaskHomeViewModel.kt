package com.io.tea.android.ui.region.home

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.io.tea.android.MainApplication
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.charge.code.ChargeCodeInputDestination
import com.io.tea.android.ui.charge.qr.ChargeQrScanDestination
import com.io.tea.android.ui.common.component.model.NotificationModel
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.ui.home.model.BannerModel
import com.io.tea.android.ui.notice.common.NoticeConverter
import com.io.tea.android.ui.notice.common.NoticeModel
import com.io.tea.android.ui.notice.detail.NoticeDetailDestination
import com.io.tea.android.ui.notice.list.NoticeListDestination
import com.io.tea.android.ui.payment.PaymentQrScanDestination
import com.io.tea.android.ui.payment.point.PaymentPointInputDestination
import com.io.tea.android.ui.region.detail.MyTeaDetailDestination
import com.io.tea.android.ui.region.home.model.CampaignModel
import com.io.tea.android.ui.region.home.model.ChargeAndPaymentButtonState
import com.io.tea.android.ui.region.home.model.ChargeAndPaymentModel
import com.io.tea.android.ui.region.home.model.PeriodModel
import com.io.tea.android.ui.region.home.model.PointModel
import com.io.tea.android.ui.region.home.model.RegionHomeModel
import com.io.tea.android.ui.region.home.model.TopBannerModel
import com.io.tea.android.ui.region.list.MyTeaListDestination
import com.io.tea.android.ui.history.HistoryDestination
import com.io.tea.android.ui.webview.WebViewDestination
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.ImageUtil
import com.io.tea.domain.response.NoticeDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyTeaHomeViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _regionHomeModelStateFlow = MutableStateFlow(RegionHomeModel())
    val regionHomeModelStateFlow: StateFlow<RegionHomeModel> = _regionHomeModelStateFlow


    init {
        // TODO: テストデータ
        val regionColor = Color(0xFF46B733)
        val pointBackgroundColor = ColorUtil.compositeOver(
            source = regionColor,
            plus = Color.White,
            alpha = 0.2f,
        )

        updateTopBannerModel(
            newModel = TopBannerModel(
                bannerImageUrl = ImageUtil.getDrawablePath(
                    context = MainApplication.appContext,
                    drawablePath = "${R.drawable.hangryangry}"
                ),
            )
        )
        updatePeriodModel(
            newModel = PeriodModel(
                regionColor = regionColor,
                couponName = "Lot One",
                period = "2023/9/30〜2024/3/20",
            )
        )

        updatePointModel(
            newModel = PointModel(
                regionColor = regionColor,
                backgroundColor = pointBackgroundColor,
                allStorePoint = 10000L,
                smallMediumStorePoint = 5000L,
            )
        )

        updateChargeAndPaymentModel(
            newModel = ChargeAndPaymentModel(
                regionColor = regionColor,
                chargeButtonState = ChargeAndPaymentButtonState.ENABLE,
                paymentButtonState = ChargeAndPaymentButtonState.ENABLE,
            )
        )
        updateCampaignModel(
            newModel = CampaignModel(
                status = "待機",
                title = "Lot第一弾",
            )
        )
        updateNotificationModel(
            newModel = NotificationModel(
                noticeList = listOf(
                    NoticeConverter.convert(
                        NoticeDTO(
                            date = "2023年1月10日",
                            TeaName = "Lot One",
                            msg = "Lot One Message",
                            url = "/img/tea/101.jpg?var=1706861388",
                            colorCode = "#FF0000FF",
                        ),
                    ),
                    NoticeConverter.convert(
                        NoticeDTO(
                            date = "2023年1月10日",
                            TeaName = "Lot Two",
                            msg = "Lot Two Message",
                            url = "/img/tea/101.jpg?var=1706861388",
                            colorCode = "#FF0000FF",
                        ),
                    ),
                    NoticeConverter.convert(
                        NoticeDTO(
                            date = "2023年1月10日",
                            TeaName = "Lot Three",
                            msg = "Lot Three Message",
                            url = "/img/tea/101.jpg?var=1706861388",
                            colorCode = "#FF0000FF",
                        ),
                    ),
                ),
            )
        )
    }

    fun onClick(id: String) {
        _navigationStateFlow.value = MyTeaListDestination(id)
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onPopBack() {
        _navigationStateFlow.value = HomeDestination("")
    }

    fun onPeriodDetailClick() {
        // TODO:
        _navigationStateFlow.value =  MyTeaDetailDestination("1")
    }

    fun onPointClick() {
        // TODO:
        _navigationStateFlow.value =  HistoryDestination
    }

    fun onChargeClick() {
        // TODO: Lotの場合にLot用のQRコードが存在していないLot（番号だけを配布）ではLotボタンを押した後にカメラの画面では無くLotコードを手入力する画面を表示する
        Log.d("★★★", "★★★onPointClick ")
        _navigationStateFlow.value =  ChargeQrScanDestination("")
    }

    fun onPaymentClick() {
        // TODO:
        _navigationStateFlow.value =  PaymentQrScanDestination("")
    }

    fun onBusinessNumberClick() {
        // TODO:
        _navigationStateFlow.value =  PaymentPointInputDestination("")
    }

    fun onCampaignClick() {
        // TODO:
        _navigationStateFlow.value =  ChargeCodeInputDestination("")
    }

    fun onBannerClick(banner: String) {
        // TODO:
        _navigationStateFlow.value = WebViewDestination(
            url = banner,
            title = "dummy"
        )
    }

    fun onNoticeDetailClick() {
        // TODO:
        _navigationStateFlow.value = NoticeListDestination(
            id = ""
        )
    }

    fun onNoticeItemClick(noticeModel: NoticeModel) {
        // TODO:
        _navigationStateFlow.value = NoticeDetailDestination(
            id = noticeModel.payName
        )
    }

    private fun updateTopBannerModel(newModel: TopBannerModel) {
        _regionHomeModelStateFlow.update {
            it.copy(topBannerModel = newModel)
        }
    }

    private fun updatePeriodModel(newModel: PeriodModel) {
        _regionHomeModelStateFlow.update { currentState ->
            currentState.copy(periodModel = newModel)
        }
    }

    private fun updatePointModel(newModel: PointModel) {
        _regionHomeModelStateFlow.update { currentState ->
            currentState.copy(pointModel = newModel)
        }
    }

    private fun updateChargeAndPaymentModel(newModel: ChargeAndPaymentModel) {
        _regionHomeModelStateFlow.update { currentState ->
            currentState.copy(chargeAndPaymentModel = newModel)
        }
    }

    private fun updateCampaignModel(newModel: CampaignModel) {
        _regionHomeModelStateFlow.update { currentState ->
            currentState.copy(campaignModel = newModel)
        }
    }

    private fun updateBannerModel(newModel: BannerModel) {
        _regionHomeModelStateFlow.update { currentState ->
            currentState.copy(bannerModel = newModel)
        }
    }

    private fun updateNotificationModel(newModel: NotificationModel) {
        _regionHomeModelStateFlow.update { currentState ->
            currentState.copy(notificationModel = newModel)
        }
    }
}