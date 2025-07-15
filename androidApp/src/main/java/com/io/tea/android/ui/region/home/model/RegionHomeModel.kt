package com.io.tea.android.ui.region.home.model

import androidx.compose.ui.graphics.Color
import com.io.tea.android.ui.common.component.model.NotificationModel
import com.io.tea.android.ui.home.model.BannerModel

data class RegionHomeModel(
    val topBannerModel: TopBannerModel = TopBannerModel.default,
    val periodModel: PeriodModel = PeriodModel.default,
    val pointModel: PointModel = PointModel.default,
    val chargeAndPaymentModel: ChargeAndPaymentModel = ChargeAndPaymentModel.default,
    val campaignModel: CampaignModel = CampaignModel.default,
    val bannerModel: BannerModel = BannerModel.default,
    val notificationModel: NotificationModel = NotificationModel.default,
)

data class TopBannerModel(
    val bannerImageUrl: String,
) {
    companion object {
        val default = TopBannerModel(
            bannerImageUrl = "",
        )
    }
}

data class PeriodModel(
    val regionColor: Color,
    val couponName: String,
    val period: String,
) {
    companion object {
        val default = PeriodModel(
            regionColor = Color.White,
            couponName = "",
            period = "",
        )
    }
}

data class PointModel(
    val regionColor: Color,
    val backgroundColor: Color,
    val allStorePoint: Long,
    val smallMediumStorePoint: Long,
) {
    companion object {
        val default = PointModel(
            regionColor = Color.White,
            backgroundColor = Color.White,
            allStorePoint = 0L,
            smallMediumStorePoint = 0L,
        )
    }

    val cardBackgroundColor: Color
        get() = regionColor.copy(alpha = 0.16f)
}

data class ChargeAndPaymentModel(
    val regionColor: Color,
    val chargeButtonState: ChargeAndPaymentButtonState,
    val paymentButtonState: ChargeAndPaymentButtonState,
) {
    companion object {
        val default = ChargeAndPaymentModel(
            regionColor = Color.White,
            chargeButtonState = ChargeAndPaymentButtonState.ENABLE,
            paymentButtonState = ChargeAndPaymentButtonState.ENABLE,
        )
    }
}

enum class ChargeAndPaymentButtonState {
    ENABLE,
    DISABLE,
    HIDDEN
}

data class CampaignModel(
    val status: String,
    val title: String,
) {
    companion object {
        val default = CampaignModel(
            status = "",
            title = "",
        )
    }
}
