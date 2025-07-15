package com.io.tea.android.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.menu.model.PushSettingType
import com.io.tea.android.ui.menu.model.PushSettingTypeModel
import com.io.tea.android.ui.menu.model.PushSettingTypeModelConverter
import com.io.tea.android.ui.search.SearchTeaListDestination
import com.io.tea.android.ui.notice.list.NoticeListDestination
import com.io.tea.android.ui.region.list.MyTeaListDestination
import com.io.tea.android.ui.webview.WebViewDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DrawerMenuViewModel : ViewModel(), KoinComponent {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    // TODO : Push通知設定の状態取得UseCaseがある想定 一旦初期値 PushSettingType.ON
    private val _pushSettingTypeModelStateFlow: MutableStateFlow<PushSettingTypeModel> =
        MutableStateFlow(currentTypeModel(PushSettingType.ON))
    val pushSettingTypeModelStateFlow: MutableStateFlow<PushSettingTypeModel> = _pushSettingTypeModelStateFlow

    // TODO : 位置情報設定の状態取得UseCase？がある想定 一旦初期値 true
    private val _isLocationSettingStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLocationSettingStateFlow: StateFlow<Boolean> =_isLocationSettingStateFlow

    private val _isLogoutStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLogoutStateFlow: StateFlow<Boolean> = _isLogoutStateFlow

    private fun currentTypeModel(selectedPushSettingType: PushSettingType): PushSettingTypeModel {
        return selectedPushSettingType.let {
            PushSettingTypeModelConverter.convert(
                it
            )
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onClickTeaAppSearch() {
        // [Lot一覧]へ遷移
        _navigationStateFlow.value = MyTeaListDestination("")
    }

    fun onClickMyTeaApp() {
        // Dummy
        _navigationStateFlow.value = SearchTeaListDestination("")
    }

    fun onClickAccount() {
        // Dummy
        _navigationStateFlow.value = WebViewDestination("https://www.google.com")
    }

    fun onClickSecurity() {
        // Dummy
        _navigationStateFlow.value = WebViewDestination("https://www.google.com")
    }

    fun onClickPasswordSetting() {
        // Dummy
        _navigationStateFlow.value = WebViewDestination("https://www.google.com")
    }

    fun onClickPushSetting(pushSettingType: PushSettingType) {
        // TODO : Push通知設定UseCaseがある想定
        val dummyPushSettingType = when (pushSettingType) {
            PushSettingType.ON -> PushSettingType.OFF
            else -> PushSettingType.ON
        }
        _pushSettingTypeModelStateFlow.value = currentTypeModel(dummyPushSettingType)
    }

    fun onClickLocationSetting(isLocationSetting: Boolean) {
        // TODO : 位置情報設定UseCase？がある想定
        _isLocationSettingStateFlow.value = isLocationSetting.not()
    }

    fun onClickUsage() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }

    fun onClickFaq() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }

    fun onClickWhat() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }

    /**
     * 利用規約
     * TOS = Term of serviceの略称
     */
    fun onClickTos() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }

    fun onClickPrivacyPolicy() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }

    fun onClickLicense() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }

    fun onClickUnsubscribe() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }

    fun onClickLogout() {
        viewModelScope.launch {
            _isLogoutStateFlow.emit(true)
        }
    }
}