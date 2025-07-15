package com.io.tea.android.ui.onboarding.personalized.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PersonalizedModel(
    @DrawableRes
    val backgroundImage: Int,
    @DrawableRes
    val image: Int,
    @StringRes
    val summary: Int,
    @StringRes
    val description: Int,
    @DrawableRes
    val indicator: Int,
    val personalizedFilterList: List<PersonalizedFilterType>,
)