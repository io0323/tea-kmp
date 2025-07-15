package com.io.tea.android.ui.search.block

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.search.model.SearchTeaListModel

@Composable
internal fun MyTeaCard(
    model: SearchTeaListModel,
    onClickCardItem: (model: SearchTeaListModel) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onClickCardItem(model) }
    ) {
        Column(
            modifier = Modifier.background(color = TeaAppTheme.colors.White)
        ) {


            Log.d("★★★imageURL", "★★★imageURL = " + model.imageURL)
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.imageURL)
                    .crossfade(true)
                    .build(),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = "loading"
            ) {
                val state = painter.state

                Log.d("★★★state", "★★★state = " + state.painter.toString())

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
            Spacer(modifier = Modifier.height(9.5.dp))
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
            ) {
                Text(
                    text = model.payName,
                    style = TeaAppTheme.typography.h2,
                    color = TeaAppTheme.colors.FontPrimary,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = model.msg,
                    style = TeaAppTheme.typography.h6,
                    color = TeaAppTheme.colors.FontSecondary,
                    maxLines = 4,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Card(
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = TeaAppTheme.colors.White)
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = model.statusColor)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.my_region_list__tea),
                    style = TeaAppTheme.typography.caption,
                    color = TeaAppTheme.colors.White,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = model.point,
                        style = TeaAppTheme.typography.title2,
                        color = TeaAppTheme.colors.White,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.common__gram),
                        style = TeaAppTheme.typography.h4,
                        color = TeaAppTheme.colors.White,
                        maxLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
    Spacer(modifier = Modifier.padding(8.dp))
}
