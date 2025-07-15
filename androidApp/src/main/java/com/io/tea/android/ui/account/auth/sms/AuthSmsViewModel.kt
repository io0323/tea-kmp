package com.io.tea.android.ui.account.auth.sms

import androidx.lifecycle.ViewModel
import com.io.tea.android.ui.account.auth.sms.model.AuthSmsModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.account.auth.sms.state.AuthSmsUiState
import com.io.tea.android.ui.account.create.model.AccountCreateModel
import com.io.tea.android.ui.account.create.state.AccountCreateUiState
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.util.StringUtil.isCntNumMatched
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthSmsViewModel : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _authSmsModelStateFlow = MutableStateFlow(AuthSmsModel.default)
    val authSmsModelStateFlow = _authSmsModelStateFlow.asStateFlow()

    private val _uiStateFlow = MutableStateFlow(AuthSmsUiState())
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onValueChange(authCode: String) {
        val isEnable = isCntNumMatched(authCode, 6)
        _authSmsModelStateFlow.update {
            it.copy(
                authCode = authCode,
                isEnable = isEnable,
            )
        }
    }

    fun onClickAuth() {
        // TODO : (fetch)認証Successなら「基本情報入力」へ遷移
        _navigationStateFlow.value = HomeDestination("")
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }
}
