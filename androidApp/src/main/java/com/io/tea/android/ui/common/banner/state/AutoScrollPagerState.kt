package com.io.tea.android.ui.common.banner.state

import com.google.accompanist.pager.PagerState

data class AutoScrollPagerState(
  val pageCount: Int,
  val initialPage: Int,
  val currentPage: Int,
  val durationMillis: Int,
  val pagerState: PagerState,
)
