package com.io.tea.android.ui.common.component.model

import androidx.annotation.StringRes

data class TabButtonItem(
    val id: Int,
    @StringRes val text: Int,
    var isSelected: Boolean = false
)
