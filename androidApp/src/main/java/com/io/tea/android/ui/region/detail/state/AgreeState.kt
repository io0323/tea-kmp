package com.io.tea.android.ui.region.detail.state

import androidx.annotation.StringRes

data class AgreeState(
    @StringRes val resText: Int,
    val linkedText: String,
    val linkTextUrl: String,
    var isChecked: Boolean = false
)
