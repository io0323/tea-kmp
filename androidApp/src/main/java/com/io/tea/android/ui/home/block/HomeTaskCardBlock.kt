package com.io.tea.android.ui.home.block

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.home.state.HomeDisplayState
import com.io.tea.android.ui.home.model.TeaModel

@Composable
internal fun HomeTeaCardBlock(
    displayType: HomeDisplayState.DisplayState,
    teaList: List<TeaModel>,
    onClickTeaItem: (TeaModel) -> Unit,
    onClickSearch: () -> Unit,
) {
    when (displayType) {
        HomeDisplayState.DisplayState.INIT -> TODO()
        HomeDisplayState.DisplayState.SUCCESS -> {
            Spacer(modifier = Modifier.height(23.dp))
            HomeTeaListBlock(
                TeaList = teaList,
                onClickCardItem = onClickTeaItem,
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeSearchBlock(
                onClickSearch = onClickSearch
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        HomeDisplayState.DisplayState.empty_ONE -> {
            Spacer(modifier = Modifier.height(23.dp))
            HomeEmptyBlockOne(
                onClickSearch = onClickSearch
            )
            Spacer(modifier = Modifier.height(57.dp))
        }

        HomeDisplayState.DisplayState.empty_TWO -> {
            Spacer(modifier = Modifier.height(23.dp))
            HomeEmptyBlockTwo(
                onClickTeaAppSearch = onClickSearch
            )
            Spacer(modifier = Modifier.height(57.dp))
        }
    }
}
