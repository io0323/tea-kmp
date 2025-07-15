package com.io.tea.android.ui.search.model

import androidx.compose.ui.graphics.Color

data class SearchTeaListModel(
    val imageURL: String,
    val imageDescription: String,
    val payName: String,
    val msg: String,
    val point: String,
    val statusColor: Color,
)
