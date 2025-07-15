package com.io.tea.android.ui.search

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.search.block.MyRegionEmptyBlock
import com.io.tea.android.ui.search.block.MyTeaCard
import com.io.tea.android.ui.search.dialog.MyRegionListFilterDialog
import com.io.tea.android.ui.search.dialog.MyRegionListSortDialog
import com.io.tea.android.ui.search.state.MyRegionListUseCaseState
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.LoadIndicator
import com.io.tea.android.ui.common.TeaAppHorizontalDivider
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.common.component.ButtonFilter
import com.io.tea.android.ui.common.component.ButtonSort

@Composable
fun MyRegionListScreen(
    viewModel: SearchTeaListViewModel,
    state: MyRegionListUseCaseState,
    isSort: Boolean,
    isFilter: Boolean,
    isPayDelete: Boolean,
) {
    val lazyListState = rememberLazyListState()
    var isShowSortDialog by remember { mutableStateOf(false) }
    var isShowFilterDialog by remember { mutableStateOf(false) }
    // TODO : empty画面確認用
    val isShowEmpty by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.my_region_list__campaign_name),
                actionsTitle = stringResource(R.string.my_region_list__pay_delete),
                actionsTextStyle = TeaAppTheme.typography.btn,
                actionsEnabled = isPayDelete,
                onPopBack = { viewModel.onPopBack() },
                onClick = { viewModel.onClickPayDelete() },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (state) {
                is MyRegionListUseCaseState.Failure -> {}
                is MyRegionListUseCaseState.Initial -> {
                    LoadIndicator(Modifier.fillMaxSize())
                }

                is MyRegionListUseCaseState.Loading -> {
                    LoadIndicator(Modifier.fillMaxSize())
                }

                is MyRegionListUseCaseState.Success -> {
                    val myRegionList = state.myTeaList
                    val myRegionSortList = state.myTeaSortList
                    val myRegionFilterMap = state.myTeaFilterMap

                    Column(
                        modifier = Modifier
                            .background(color = TeaAppTheme.colors.Grey100)
                            .padding(horizontal = 24.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ButtonSort(
                                isEnabled = true,
                                isSort = isSort,
                                onClick = {
                                    viewModel.onClickSort(isSort)
                                    isShowSortDialog = true
                                },
                            )
                            ButtonFilter(
                                isEnabled = true,
                                isFilter = isFilter,
                                onClick = {
                                    viewModel.onClickFilter()
                                    isShowFilterDialog = true
                                },
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // TODO : Dividerを要否を確認 https://github.com/giftpad/region-pay-app-v2/pull/190#discussion_r1645650029
                        TeaAppHorizontalDivider(
                            color = TeaAppTheme.colors.Grey300,
                        )
                        Box {
                            if (isShowEmpty) {
                                MyRegionEmptyBlock()
                            } else {
                                LazyColumn(
                                    state = lazyListState,
                                ) {
                                    items(myRegionList) { item ->
                                        MyTeaCard(
                                            model = item,
                                            onClickCardItem = { viewModel.onClickCardItem(item) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                    if (isShowSortDialog) {
                        MyRegionListSortDialog(
                            viewModel = viewModel,
                            myRegionSortList = myRegionSortList,
                            onDismissClick = {
                                isShowSortDialog = false
                            },
                            onClickFilter = {
                                isShowSortDialog = false
                            }
                        )
                    }
                    if (isShowFilterDialog) {
                        MyRegionListFilterDialog(
                            viewModel = viewModel,
                            myRegionFilterMap = myRegionFilterMap,
                            onDismissClick = {
                                isShowFilterDialog = false
                            },
                            onClickFilter = {
                                isShowFilterDialog = false
                                viewModel.onClickFilter()
                            }
                        )
                    }
                }
            }
        }
    }
}
