package com.io.tea.android.ui.region.home.block

import androidx.compose.runtime.Composable
import com.io.tea.android.ui.home.model.BannerModel

@Composable
internal fun BannerBlock(
    bannerModel: BannerModel,
    onClick: (String) -> Unit,
) {
//    val pagerState = rememberPagerState(
//        initialPage = 0,
//        pageCount = { bannerModel.imageURL.size }
//    )
//
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    val cardWidth = 234.dp
//    val contentPadding = (screenWidth - cardWidth) / 2
//
//    Column(
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(bannerModel.regionColor)
//            .padding(vertical = 24.dp)
//    ) {
//        HorizontalPager(
//            state = pagerState,
//            pageSize = PageSize.Fixed(pageSize = cardWidth),
//            pageSpacing = 16.dp,
//            contentPadding = PaddingValues(horizontal = contentPadding),
//            modifier = Modifier.fillMaxWidth()
//        ) { page ->
//            OutlinedCard(
//                border = BorderStroke(
//                    width = 2.dp,
//                    color = AppTheme.colors.White,
//                ),
//                modifier = Modifier
//                    .width(cardWidth)
//                    .height(50.dp)
//                    .clickable(
//                        onClick = {
//                            onClick(bannerModel.urlList[page])
//                        }
//                    )
//            ) {
//                AsyncImage(
//                    model = bannerModel.imageUrlList[page],
//                    contentdescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(
//                space = 10.dp,
//                alignment = Alignment.CenterHorizontally
//            ),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            bannerModel.imageUrlList.forEachIndexed { index, _ ->
//                val color = if (index == pagerState.currentPage) {
//                    AppTheme.colors.Blue
//                } else {
//                    AppTheme.colors.Grey300
//                }
//                Box(
//                    modifier = Modifier
//                        .size(10.dp)
//                        .background(color, CircleShape)
//                )
//            }
//        }
//    }
}
