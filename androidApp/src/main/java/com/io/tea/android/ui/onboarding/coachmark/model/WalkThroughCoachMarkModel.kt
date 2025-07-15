package com.io.tea.android.ui.onboarding.coachmark.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WalkThroughCoachMarkModel(
    @DrawableRes
    val image: Int,
    @StringRes
    val summary: Int,
    @StringRes
    val description: Int,
    @DrawableRes
    val indicator: Int,
    @StringRes
    val buttonText: Int,
)