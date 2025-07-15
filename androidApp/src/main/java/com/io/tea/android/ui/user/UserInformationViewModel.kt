package com.io.tea.android.ui.user

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.user.model.UpdateAnimationModel
import com.io.tea.android.ui.user.model.UserInformationModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserInformationViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _userInformationStateFlow = MutableStateFlow(UserInformationModel.empty)
    val userInformationStateFlow: StateFlow<UserInformationModel> = _userInformationStateFlow

    private val _updateAnimationStateFlow = MutableStateFlow(UpdateAnimationModel())
    val updateAnimationStateFlow: StateFlow<UpdateAnimationModel> = _updateAnimationStateFlow

    init {
        // TODO: サンプルデータ
        _userInformationStateFlow.value = UserInformationModel(
            birth = "2000年5月",
            gender = "選択しない",
            zip = "123456",
            address = "大阪府大阪市浪速区",
            isJapanCountryCode = true,
        )
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onClick() {
        // TODO: アニメーションのテストのために、サンプルデータを流す。
        _updateAnimationStateFlow.value = UpdateAnimationModel(
            isVisible = true,
        )
    }
    fun completeAnimation() {
        _updateAnimationStateFlow.value = UpdateAnimationModel(
            isVisible = false,
        )
    }
}
