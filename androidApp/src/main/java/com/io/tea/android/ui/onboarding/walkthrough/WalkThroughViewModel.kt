package com.io.tea.android.ui.onboarding.walkthrough

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.ui.onboarding.walkthrough.model.WalkThroughModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalkThroughViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _currentPageStateFlow = MutableStateFlow(0)
    val currentPageStateFlow: StateFlow<Int> get() = _currentPageStateFlow

    val modelList = listOf(
        WalkThroughModel(
            com.google.android.gms.base.R.drawable.common_full_open_on_phone,
            R.string.walk_through__image_one,
            R.string.walk_through__image_one_description,
            com.google.android.gms.base.R.drawable.googleg_standard_color_18,
            R.string.common__next
        ),
        WalkThroughModel(
            com.google.android.gms.base.R.drawable.common_full_open_on_phone,
            R.string.walk_through__image_two,
            R.string.walk_through__image_two_description,
            com.google.android.gms.base.R.drawable.googleg_standard_color_18,
            R.string.common__next
        ),
        WalkThroughModel(
            com.google.android.gms.base.R.drawable.common_full_open_on_phone,
            R.string.walk_through__image_three,
            R.string.walk_through__image_three_description,
            com.google.android.gms.base.R.drawable.googleg_standard_color_18,
            R.string.common__next
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
