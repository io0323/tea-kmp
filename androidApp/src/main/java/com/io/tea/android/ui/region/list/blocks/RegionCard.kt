package com.io.tea.android.ui.region.list.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.io.tea.android.ui.region.list.RegionListViewModel
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.domain.response.RegionDTO

@Composable
fun RegionListCard(
    prefecture: String,
    title: String,
    periodLabel: String,
    period: String,
    imageUrl: String,
    isRegionAdded: Boolean,
    onClickCardItem: (prefecture: String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp,
                vertical = 12.dp
            )
            .height(106.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onClickCardItem(prefecture) }
    ) {
        Row {
            RegionListCardImage(
                imageUrl = imageUrl,
                title = title,
                isRegionAdded = isRegionAdded
            )
            RegionListCardDescription(
                prefecture = prefecture,
                title = title,
                periodLabel = periodLabel,
                period = period
            )
        }
    }
}

@Composable
private fun RegionListCardImage(
    imageUrl: String,
    title: String,
    isRegionAdded: Boolean,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(142.dp)
            .fillMaxHeight()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = title,
            modifier = Modifier.fillMaxSize()
        )
        if (isRegionAdded) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colors.Base.regionListAddedBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.region_list__added_label),
                    style = TeaAppTheme.typography.regionListCardImageLabel,
                )
            }
        }
    }
}

@Composable
private fun RegionListCardDescription(
    prefecture: String,
    title: String,
    periodLabel: String,
    period: String,
) {
    Column(
        Modifier
            .background(color = Colors.Base.regionListCardBackground)
            .padding(
                horizontal = 12.dp,
                vertical = 9.dp
            )
            .fillMaxWidth()
    ) {
        Text(
            text = prefecture,
            color = Colors.Font.primary,
            style = TeaAppTheme.typography.regionListCardPrefecture
        )
        Text(
            text = title,
            style = TeaAppTheme.typography.regionListCardPrefecture
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = periodLabel,
            style = TeaAppTheme.typography.regionListCardPeriodLabel
        )
        Text(
            text = period,
            style = TeaAppTheme.typography.regionListCardPeriod
        )
    }
}

@Composable
internal fun RegionCard(
    viewModel: RegionListViewModel,
    region: RegionDTO,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(120.dp),
        onClick = { viewModel.onClickRegionCardItem(region) }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RegionIcon(region.isAdded) {
                RegionImage(imageURL = region.image)
            }
            Spacer(Modifier.width(8.dp))
            Column {
                Text(region.name, fontWeight = FontWeight.Bold)
                Text("説明文がここに入ります")
            }
        }
    }
}

@Composable
private fun RegionIcon(isAdded: Boolean, image: @Composable () -> Unit) {
    Box {
        image()
        if (isAdded) {
            AddedStatus(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = (5).dp, y = 5.dp)  // 例として右上に配置し、位置を調整
            )
        }
    }
}

@Composable
private fun AddedStatus(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(1.dp, Color.DarkGray, RoundedCornerShape(10.dp))
            .background(Color.Gray, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text("追加済", Modifier.padding(4.dp))
    }
}

@Composable
private fun RegionImage(imageURL: String = "") {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageURL)
            .crossfade(true)
            .build(),
        modifier = Modifier.size(120.dp),
        contentScale = ContentScale.Crop,
        contentDescription = "loading"
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp)
                )
            }
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}
