package com.io.tea.android.ui.home.block

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.search.block.MyTeaItem
import com.io.tea.android.ui.home.model.TeaModel

@Composable
fun HomeTeaListBlock(
    TeaList: List<TeaModel>,
    onClickCardItem: (TeaModel) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { TeaList.size }
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = screenWidth - 48.dp
    val contentPadding = (screenWidth - cardWidth) / 2

    HorizontalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(pageSize = cardWidth),
        pageSpacing = cardWidth * 0.05f,
        contentPadding = PaddingValues(horizontal = contentPadding),
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) { page ->
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            MyTeaItem(
                model = TeaList[page],
                onClickCardItem = { onClickCardItem(it) }
            )
        }
    }
}
