package com.io.tea.android.ui.delete.account.auth

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.common.state.SmsSource
import com.io.tea.android.ui.delete.account.auth.state.DeleteAccountAuthState
import com.io.tea.android.ui.delete.account.auth.state.DeleteAccountAuthType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeleteAccountAuthViewModel(
    source: SmsSource
) : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _deleteAccountAuthsStateFlow = MutableStateFlow(
        DeleteAccountAuthState(authType = DeleteAccountAuthType.fromLOptInSmsSource(source = source))
    )
    val deleteAccountAuthsStateFlow: StateFlow<DeleteAccountAuthState> =
        _deleteAccountAuthsStateFlow

    private val _countryCodeStateFlow = MutableStateFlow("")
    val countryCodeStateFlow: MutableStateFlow<String> = _countryCodeStateFlow

    init {
        _countryCodeStateFlow.value = "日本 (+81)"
    }

    fun onClickCancel() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onClickLogin() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}