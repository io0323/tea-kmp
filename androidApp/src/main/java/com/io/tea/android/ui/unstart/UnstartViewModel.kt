package com.io.tea.android.ui.unstart

import androidx.lifecycle.ViewModel
import com.io.tea.android.ui.region.list.MyTeaListDestination
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.home.HomeDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UnstartViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    fun onClick(id: String) {
        _navigationStateFlow.value = MyTeaListDestination(id)
    }

    fun onPopBack() {
        _navigationStateFlow.value = HomeDestination("")
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}