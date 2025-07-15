package com.io.tea.android.resource

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// TODO : MyApplicationThemeからの移植
internal val shapes: Shapes = Shapes(
    small = RoundedCornerShape(size = 4.dp),
    medium = RoundedCornerShape(size = 8.dp),
    large = RoundedCornerShape(size = 12.dp)
)
