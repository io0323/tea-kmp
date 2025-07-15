package com.io.tea.android.ui.region.detail.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun CouponImage(
    imageUrl: String,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Crop,
    color: Color = TeaAppTheme.colors.Blue,
    onClick: (imageUrl: String) -> Unit,
) {
    // NOTE : Imageタップでのアクションは現行想定してないとのこと
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .height(232.dp)
            .fillMaxWidth()
            .background(color)
//            .clickable { onClick(imageUrl) }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
    }
}
