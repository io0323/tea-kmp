package com.io.tea.android.ui.region.list.blocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.domain.response.RegionDTO
import com.io.tea.domain.usecase.RegionListWithHeader

@Composable
fun RegionListWithPrefecture(
    regionListWithHeaderList: List<RegionListWithHeader>,
    onClickRegionCardItem: (region: RegionDTO) -> Unit,
) {
    regionListWithHeaderList.forEach { (prefecture, regionList) ->
        Column {
            PrefectureHeader(prefecture = prefecture)
            regionList.map { region ->
                RegionListCard(
                    prefecture = region.prefecture,
                    title = region.name,
                    periodLabel = stringResource(R.string.region_list__period_label),
                    period = "2024/4/1〜2024/5/6",
                    imageUrl = region.image,
                    isRegionAdded = region.isAdded,
                    onClickCardItem = {
                        onClickRegionCardItem(region)
                    },
                )
            }
        }
    }
}

@Composable
private fun PrefectureHeader(prefecture: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(Colors.Base.regionListPrefectureHeader)
            .padding(
                horizontal = 24.dp,
            )
    ) {
        Text(
            text = prefecture,
            textAlign = TextAlign.Center,
            color = Colors.Font.primary,
            fontSize = 13.sp,
            fontWeight = FontWeight.W700
        )
    }
}

@Composable
fun EmptyRegionList() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "Description",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .width(102.dp)
                .height(87.dp)
        )
        Text(
            text = stringResource(R.string.region_list__empty_region_label),
            textAlign = TextAlign.Center,
            style = TeaAppTheme.typography.regionListEmptyLabel,
        )
    }
}
