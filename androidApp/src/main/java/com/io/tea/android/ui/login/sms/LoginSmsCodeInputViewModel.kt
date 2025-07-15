package com.io.tea.android.ui.login.sms

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.common.state.SmsSource
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.ui.login.model.LogInSmsCodeInputModel
import com.io.tea.android.ui.login.state.LogInSmsAuthType
import com.io.tea.android.ui.login.state.LogInSmsState
import com.io.tea.android.util.StringUtil.isCntNumMatched
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LogInSmsCodeInputViewModel(source: SmsSource) : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _loginSmsStateFlow = MutableStateFlow(
        LogInSmsState(authType = LogInSmsAuthType.fromLogInSmsSource(source = source))
    )
    val loginSmsStateFlow: StateFlow<LogInSmsState> = _loginSmsStateFlow

    private val _loginSmsCodeInputModelStateFlow = MutableStateFlow(LogInSmsCodeInputModel.default)
    val loginSmsCodeInputModelStateFlow: StateFlow<LogInSmsCodeInputModel> =
        _loginSmsCodeInputModelStateFlow

    fun onValueChange(authCode: String) {
        val isEnable = isCntNumMatched(authCode, 6)
        _loginSmsCodeInputModelStateFlow.update {
            it.copy(
                authCode = authCode,
                isEnable = isEnable,
            )
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onClickAuth() {
        _navigationStateFlow.value = HomeDestination("")
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
