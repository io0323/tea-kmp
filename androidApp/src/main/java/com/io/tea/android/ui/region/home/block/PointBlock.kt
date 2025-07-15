package com.io.tea.android.ui.region.home.block

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.region.home.model.PointModel
import com.io.tea.android.util.StringUtil

@Composable
internal fun PointBlock(
    pointModel: PointModel,
    onPointClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = TeaAppTheme.colors.White),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {

            Card(
                colors = CardDefaults.cardColors(containerColor = pointModel.cardBackgroundColor),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .clickable(onClick = onPointClick)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        val allPoint = pointModel.allStorePoint + pointModel.smallMediumStorePoint
                        Text(
                            text = StringUtil.formatComma(allPoint),
                            style = TeaAppTheme.typography.title2,
                            color = pointModel.regionColor,
                            modifier = Modifier.padding(top = 7.5.dp, bottom = 1.5.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = stringResource(R.string.common__gram),
                            style = TeaAppTheme.typography.h2,
                            color = pointModel.regionColor,
                            modifier = Modifier.padding(top = 15.5.dp, bottom = 9.5.dp)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    ) {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = "icon",
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))

            StorePoint(
                resourceId = R.string.region_home__point_all_store,
                point = pointModel.allStorePoint,
            )
            Spacer(modifier = Modifier.height(4.dp))

            StorePoint(
                resourceId = R.string.region_home__point_small_medium_store,
                point = pointModel.smallMediumStorePoint,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StorePoint(@StringRes resourceId: Int, point: Long) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(resourceId),
            style = TeaAppTheme.typography.h6,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = StringUtil.formatComma(point),
            style = TeaAppTheme.typography.subtitle3,
        )
        Text(
            text = stringResource(R.string.common__gram),
            style = TeaAppTheme.typography.h6,
        )
    }
}
