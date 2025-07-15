package com.io.tea.android.ui.user.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class UpdateAnimationModel(
    val isVisible: Boolean = false,
    val durationMillis: Int = DURATION_MILLS,
    val offsetY: Int = 0
) {
    companion object {
        private const val SHOWING_MILLS: Long = 5000L
        const val DURATION_MILLS: Int = 700
        const val DISPLAY_MILLS: Long = DURATION_MILLS + SHOWING_MILLS

        val componentHeight: Dp = 152.dp // height: 56 + padding: 16 + bottomHeight: 80
    }
}
