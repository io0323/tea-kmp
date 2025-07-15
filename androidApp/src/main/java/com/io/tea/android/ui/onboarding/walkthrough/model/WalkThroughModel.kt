package com.io.tea.android.ui.onboarding.walkthrough.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WalkThroughModel(
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