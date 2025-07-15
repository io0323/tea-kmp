package com.io.tea.android.ui.common.component.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

data class BarcodeBackgroundModel(
    val rectangleOffsetX: Dp,
    val rectangleOffsetY: Dp,
    val cornerLength: Dp,
    val cornerStrokeWidth: Dp,
    val cornerColor: Color,
    val backgroundColor: Color
)
