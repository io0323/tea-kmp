package com.io.tea.android.ui.common.banner.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.io.tea.android.ui.common.banner.state.AutoScrollPagerState
import com.io.tea.android.util.MathUtil.floorMod
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun rememberAutoScrollPagerState(
    pageCount: Int,
    initialPage: Int,
    durationMillis: Int,
    onChangedPage: ((Int) -> Unit)? = null,
): AutoScrollPagerState {
    val startIndex = Int.MAX_VALUE / 2 + initialPage
    val pagerState = rememberPagerState(
        initialPage = startIndex,
    )
    val currentPage = (pagerState.currentPage - Int.MAX_VALUE / 2).floorMod(pageCount)
    val scope = rememberCoroutineScope()
    val scrollTo = { state: PagerState, page: Int ->
        scope.launch {
            if (!state.isScrollInProgress) {
                pagerState.animateScrollToPage(page)
            }
        }
    }
    LaunchedEffect(pagerState.currentPage) {
        onChangedPage?.invoke(currentPage)
        delay(durationMillis.toLong())
        scrollTo(pagerState, pagerState.currentPage + 1)
    }
    return AutoScrollPagerState(
        pageCount,
        initialPage,
        currentPage,
        durationMillis,
        pagerState,
    )
}
