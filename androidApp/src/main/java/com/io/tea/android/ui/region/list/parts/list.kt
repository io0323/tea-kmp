package com.io.tea.android.ui.region.list.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.region.list.blocks.RegionCard
import com.io.tea.android.ui.region.list.RegionListViewModel
import com.io.tea.android.ui.common.state.ButtonState
import com.io.tea.android.ui.region.list.state.RegionListLoadingState
import com.io.tea.domain.usecase.RegionListWithHeader

@Composable
internal fun HeaderList(
    onClickNoAddedButton: () -> Unit,
    noAddButtonState: ButtonState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 5.dp, end = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            NoAddedButton(
                onClick = onClickNoAddedButton,
                noAddButtonState = noAddButtonState
            )
            PrefectureButton()
        }
        DeleteButton()
    }
}

@Composable
internal fun RegionList(
    viewModel: RegionListViewModel,
    regionListWithHeaderList: List<RegionListWithHeader>,
    loadingState: RegionListLoadingState,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            regionListWithHeaderList.forEach { (prefecture, regionCards) ->
                item(key = prefecture) {
                    PrefectureText(prefecture = prefecture)
                }
                items(regionCards, key = { item -> item.id }) { regionCard ->
                    Spacer(modifier = Modifier.height(10.dp))
                    RegionCard(
                        viewModel = viewModel,
                        region = regionCard,
                    )
                }
            }
        }

        if (loadingState is RegionListLoadingState.Loading || loadingState is RegionListLoadingState.Initial) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.White.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}