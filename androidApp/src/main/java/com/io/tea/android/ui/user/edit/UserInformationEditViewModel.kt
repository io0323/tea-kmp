package com.io.tea.android.ui.user.edit

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.user.model.Birth
import com.io.tea.android.ui.user.model.UserInformationEditModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserInformationEditViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _userInformationEditModelStateFlow =
        MutableStateFlow(UserInformationEditModel.empty)
    val userInformationEditModelStateFlow: StateFlow<UserInformationEditModel> =
        _userInformationEditModelStateFlow

    init {
        // TODO: サンプルデータ
        _userInformationEditModelStateFlow.value = UserInformationEditModel(
            birth = Birth.from("2000年5月"),
            gender = "選択しない",
            zip = "123456",
            address = "大阪府大阪市浪速区",
            isJapanCountryCode = true,
        )
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
