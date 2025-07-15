package com.io.tea.android.ui.onboarding.personalized

import androidx.lifecycle.ViewModel
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.onboarding.personalized.model.PersonalizedFilterType
import com.io.tea.android.ui.onboarding.personalized.model.PersonalizedModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PersonalizedViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    // TODO : UseCaseから取得
    private val personalizedKeywordTypeList = listOf(
        PersonalizedFilterType.AAA,
        PersonalizedFilterType.BBB,
        PersonalizedFilterType.CCC,
        PersonalizedFilterType.DDD,
        PersonalizedFilterType.EEE,
    )
    val model = PersonalizedModel(
            android.R.drawable.ic_menu_close_clear_cancel,
            android.R.drawable.ic_menu_close_clear_cancel,
            R.string.personalized__image_one,
            R.string.personalized__image_one_description,
            android.R.drawable.ic_menu_close_clear_cancel,
            personalizedKeywordTypeList
        )

    fun onClickPersonalizedFilter(filter: PersonalizedFilterType) {
        filter.filter
//        _navigationStateFlow.value = PhoneNumberInputDestination("")
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}