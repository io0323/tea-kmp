package com.io.tea.android.ui.security

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SecurityViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _isSecurityPassCodeLockEnabled = MutableStateFlow(false)
    val isSecurityPassCodeLockEnabled: StateFlow<Boolean> = _isSecurityPassCodeLockEnabled

    fun onSecurityPassCodeLockChanged(isEnabled: Boolean) {
        _isSecurityPassCodeLockEnabled.value = isEnabled
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}