package com.io.tea.android.ui.onboarding.coachmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.ui.onboarding.coachmark.model.WalkThroughCoachMarkModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalkThroughCoachMarkViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _currentPageStateFlow = MutableStateFlow(0)
    val currentPageStateFlow: StateFlow<Int> get() = _currentPageStateFlow

    val modelList = listOf(
        WalkThroughCoachMarkModel(
            android.R.drawable.ic_menu_close_clear_cancel,
            R.string.walk_through__image_one,
            R.string.walk_through__image_one_description,
            android.R.drawable.ic_menu_close_clear_cancel,
            R.string.common__next
        ),
        WalkThroughCoachMarkModel(
            android.R.drawable.ic_menu_close_clear_cancel,
            R.string.walk_through__image_two,
            R.string.walk_through__image_two_description,
            android.R.drawable.ic_menu_close_clear_cancel,
            R.string.common__next
        ),
        WalkThroughCoachMarkModel(
            android.R.drawable.ic_menu_close_clear_cancel,
            R.string.walk_through__image_four,
            R.string.walk_through__image_four_description,
            android.R.drawable.ic_menu_close_clear_cancel,
            R.string.common__start
        )
    )

    fun onPageChanged(newPage: Int) {
        viewModelScope.launch {
            _currentPageStateFlow.emit(newPage)
        }
    }

    fun onClickSkip() {
        _navigationStateFlow.value = HomeDestination("")
    }

    fun onClickStart() {
        _navigationStateFlow.value = HomeDestination("")
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}