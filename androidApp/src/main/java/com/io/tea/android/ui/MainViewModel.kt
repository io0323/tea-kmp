package com.io.tea.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.nav.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _startDestinationRoute = MutableStateFlow<String?>(null)
    val startDestinationRoute: StateFlow<String?> = _startDestinationRoute

    private val _navigateToStateFlow = MutableStateFlow<Destination?>(null)
    val navigateToStateFlow: StateFlow<Destination?> = _navigateToStateFlow

    init {
        viewModelScope.launch {
        }
    }

    fun onCreate(graphRoute: String) {
        viewModelScope.launch {
            setStartDestinationRoute(graphRoute)
        }
    }

    fun onResume() {
    }

    fun onStart() {
    }

    fun onDestroy() {
    }

    fun onNewIntent() {
    }

    private fun setStartDestinationRoute(graphRoute: String) {
        _startDestinationRoute.value = graphRoute
    }

    fun completeToNavigation() {
        _navigateToStateFlow.update { null }
    }
}