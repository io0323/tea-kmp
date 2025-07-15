package com.io.tea.android.ui.common.banner.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.size.Dimension
import coil.size.Size
import com.io.tea.android.ui.LocalWindowSize
import com.io.tea.android.util.event.clickableSingle
import com.io.tea.android.util.coil.rememberCustomImagePainter

const val BANNER_WIDTH = 1080

@Composable
fun RememberBannerImage(
    imageUrl: String,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
          .wrapContentHeight()
          .fillMaxWidth()
          .clickableSingle(onClick = onClick),
    ) {
        BannerImage(
            painter = rememberCustomImagePainter(
                imageUrl,
                size = Size(BANNER_WIDTH, Dimension.Undefined),
            ),
            contentdescription = "banner",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(if (LocalWindowSize.current.isTablet) 16.dp else 8.dp)),
        )
    }
}
