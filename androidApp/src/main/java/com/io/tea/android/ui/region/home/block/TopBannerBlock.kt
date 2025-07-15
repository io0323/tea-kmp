package com.io.tea.android.ui.region.home.block

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.io.tea.android.ui.common.component.ButtonChevron
import com.io.tea.android.ui.region.home.model.TopBannerModel

@Composable
internal fun TopBannerBlock(
    topBannerModel: TopBannerModel,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(topBannerModel.bannerImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        ButtonChevron(
            onClick = onClick,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp
                )
        )
    }
}
