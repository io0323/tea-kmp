package com.io.tea.android.ui.common.banner.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ProgressLine(
    progress: Float,
    color: Color,
    backgroundColor: Color,
    width: Dp,
    height: Dp,
) {
    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .clip(CircleShape),
    ) {
        Canvas(
            modifier = Modifier.size(
                width = width,
                height = height,
            ),
        ) {
            val yOffset = size.height / 2
            drawLine(backgroundColor, Offset(0f, yOffset), Offset(size.width, yOffset), size.height)
            drawLine(
                color,
                Offset(0f, yOffset),
                Offset(progress * size.width, yOffset),
                size.height
            )
        }
    }
}
