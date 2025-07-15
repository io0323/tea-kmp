package com.io.tea.android.ui.common

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun TeaAppHorizontalDivider(
    thickness: Dp = 1.dp,
    color: Color = TeaAppTheme.colors.Grey100,
) {
    HorizontalDivider(
        thickness = thickness,
        color = color,
    )
}
