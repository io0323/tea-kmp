package com.io.tea.android.ui.home.block

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.LocalWindowSize
import com.io.tea.android.ui.home.model.BannerModel
import com.io.tea.android.ui.common.banner.component.InfiniteAutoScrollPager
import com.io.tea.android.ui.common.banner.component.InfiniteAutoScrollProgressLine
import com.io.tea.android.ui.common.banner.component.RememberBannerImage
import com.io.tea.android.ui.common.banner.util.rememberAutoScrollPagerState

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
internal fun HomeBannerBlock(
    bannerList: List<BannerModel>,
    onClickItem: (BannerModel) -> Unit,
//  onShowItem: (BannerModel) -> Unit,
) {
    val isTablet = LocalWindowSize.current.isTablet
    if (bannerList.size > 1 || isTablet) {
        val bannerScrollState = rememberAutoScrollPagerState(
            pageCount = bannerList.size,
            initialPage = 0,
            durationMillis = 5000,
            onChangedPage = { page ->
//          bannerList.getOrNull(page)?.let {
//            onShowItem(it)
//          }
            },
        )
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            // NOTE : サイズ調整中 バナー以下の確認のためバナーサイズ縮小中
//            val phoneWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() } - 32.dp
            val phoneWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() } - 320.dp
            InfiniteAutoScrollPager(
                state = bannerScrollState,
                contentWidth = if (isTablet) 488.dp else phoneWidth,
                itemSpacing = if (isTablet) 8.dp else 8.dp,
            ) { i ->
                val item = bannerList[i]
                RememberBannerImage(
                    imageUrl = item.imageURL,
                ) {
                    onClickItem.invoke(item)
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            InfiniteAutoScrollProgressLine(
                state = bannerScrollState,
                barWidth = 10.dp,
                barHeight = 4.dp,
                barSpace = 2.dp,
                barColor = TeaAppTheme.colors.Blue,
                barBackgroundColor = TeaAppTheme.colors.BackgroundGray,
            )
        }
    } else if (bannerList.size == 1) {
        LaunchedEffect(Unit) {
//        onShowItem(bannerList[0])
        }
        BoxWithConstraints(Modifier.fillMaxWidth()) {
            RememberBannerImage(
                imageUrl = bannerList[0].imageURL,
            ) {
                onClickItem.invoke(bannerList[0])
            }
        }
    }
}
