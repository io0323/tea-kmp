package com.io.tea.android.ui.common.component.model

import androidx.compose.ui.graphics.Color

data class RegionCardModel(
    val imageURL: String,
    val imageDescription: String,
    val payName: String,
    val msg: String,
    val point: String,
    val regionColor: Color,
)