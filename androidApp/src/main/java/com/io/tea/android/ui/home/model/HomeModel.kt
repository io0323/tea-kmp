package com.io.tea.android.ui.home.model

import com.io.tea.android.ui.common.component.model.NotificationModel

data class HomeModel(
    val bannerList: List<BannerModel>,
    val teaList: List<TeaModel>,
    val notificationModel: NotificationModel,
) {
    companion object {
        val default: HomeModel = HomeModel(
            bannerList = emptyList(),
            teaList = emptyList(),
            notificationModel = NotificationModel.default,
        )
    }
}
