package com.io.tea.android.ui.home.state

import com.io.tea.android.ui.common.component.model.NotificationModel
import com.io.tea.android.ui.home.model.BannerModel
import com.io.tea.android.ui.home.model.TeaModel

sealed class HomeUseCaseState {
    data object Initial : HomeUseCaseState()
    data object Loading : HomeUseCaseState()
    data class Success(
        val bannerList: List<BannerModel>,
        val teaList: List<TeaModel>,
        val notificationModel: NotificationModel = NotificationModel.default,
    ) : HomeUseCaseState()

    data class Failure(val errorMessage: String) : HomeUseCaseState()
}
