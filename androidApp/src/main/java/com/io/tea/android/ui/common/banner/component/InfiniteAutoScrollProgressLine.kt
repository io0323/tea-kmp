package com.io.tea.android.ui.common.banner.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.io.tea.android.ui.common.banner.state.AutoScrollPagerState

@Composable
fun InfiniteAutoScrollProgressLine(
    state: AutoScrollPagerState,
    barWidth: Dp,
    barHeight: Dp,
    barSpace: Dp,
    barColor: Color,
    barBackgroundColor: Color,
) {
    val currentPage = state.currentPage
    Row {
        repeat(state.pageCount) { i ->
            val progress = when (i) {
                currentPage -> 1f
                else -> 0f
            }
            ProgressLine(
                progress = progress,
                color = barColor,
                backgroundColor = barBackgroundColor,
                width = barWidth,
                height = barHeight,
            )
            if (i != state.pageCount - 1) {
                Spacer(modifier = Modifier.width(barSpace))
            }
        }
    }
}
