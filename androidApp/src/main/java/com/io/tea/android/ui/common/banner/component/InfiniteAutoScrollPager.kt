package com.io.tea.android.ui.common.banner.component

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.io.tea.android.ui.common.banner.state.AutoScrollPagerState
import com.io.tea.android.util.MathUtil.floorMod

@Composable
fun InfiniteAutoScrollPager(
    state: AutoScrollPagerState,
    contentWidth: Dp? = null,
    itemSpacing: Dp = 0.dp,
    userScrollEnabled: Boolean = true,
    content: @Composable PagerScope.(Int) -> Unit,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val contentPadding = if (contentWidth != null && maxWidth >= contentWidth) {
            PaddingValues(horizontal = (maxWidth - contentWidth) / 2)
        } else {
            PaddingValues(0.dp)
        }
        HorizontalPager(
            count = Int.MAX_VALUE,
            state = state.pagerState,
            contentPadding = contentPadding,
            itemSpacing = itemSpacing,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = userScrollEnabled,
        ) { index ->
            val i = (index - Int.MAX_VALUE / 2).floorMod(state.pageCount)
            content(i)
        }
    }
}
