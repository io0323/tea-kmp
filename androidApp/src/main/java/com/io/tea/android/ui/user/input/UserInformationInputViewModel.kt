package com.io.tea.android.ui.user.input

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserInformationInputViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}