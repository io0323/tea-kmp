package com.io.tea.android.nav.bottom

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainBottomNavViewModel(
    bottomNavDestinationList: List<BottomNavDestination>,
) : ViewModel() {

    private val _bottomNavListModelStateFlow = MutableStateFlow(
        bottomNavDestinationList.map {
            BottomNavItemModel(it)
        },
    )
    val bottomNavListModelStateFlow: StateFlow<List<BottomNavItemModel>> =
        _bottomNavListModelStateFlow.asStateFlow()
}
