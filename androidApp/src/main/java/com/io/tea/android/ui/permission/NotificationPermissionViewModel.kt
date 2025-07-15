package com.io.tea.android.ui.permission

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotificationPermissionViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _nextStateFlow  = MutableStateFlow(false)

    fun onClickNextButton() {
        _nextStateFlow.value = true
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}