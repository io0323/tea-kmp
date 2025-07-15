package com.io.tea.android.ui.region.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.region.list.blocks.CodeButton
import com.io.tea.android.ui.region.list.blocks.EmptyRegionList
import com.io.tea.android.ui.region.list.blocks.RegionListFilterDialog
import com.io.tea.android.ui.region.list.blocks.RegionListFilters
import com.io.tea.android.ui.region.list.blocks.RegionListWithPrefecture
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.LoadIndicator
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.region.list.model.RegionFilterModel
import com.io.tea.android.ui.region.list.model.RegionListModel
import com.io.tea.android.ui.region.list.state.RegionListLoadingState
import com.io.tea.domain.response.RegionDTO

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegionListScreen(
    model: RegionListModel,
    loading: RegionListLoadingState,
    onPopBack: () -> Unit = {},
    onClickTeaAppDelete: () -> Unit = {},
    onChecked: (Boolean) -> Unit,
    onClickCode: () -> Unit,
    onCheckedFilter: (RegionFilterModel) -> Unit,
    onFilterClearClick: () -> Unit,
    onFilterClick: () -> Unit,
    onClickRegionCardItem: (region: RegionDTO) -> Unit,
) {
    val style = if (model.isEnableTeaAppDelete) {
        TeaAppTheme.typography.regionListDeleteEnable
    } else {
        TeaAppTheme.typography.regionListDeleteDisable
    }
    // TODO: ダイアログの表示管理は仮実装
    var shouldShowDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.region_list__header_title),
                onPopBack = onPopBack,
                actionsTitle = stringResource(R.string.region_list__header_button_title),
                actionsTextStyle = style,
                actionsEnabled = when (loading) {
                    RegionListLoadingState.Success -> {
                        model.isEnableTeaAppDelete
                    }

                    else -> false
                },
                onClick = onClickTeaAppDelete,
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            when (loading) {
                is RegionListLoadingState.Failure -> {}
                RegionListLoadingState.Initial -> {
                    LoadIndicator(Modifier.fillMaxSize())
                }

                RegionListLoadingState.Loading -> {
                    LoadIndicator(Modifier.fillMaxSize())
                }

                RegionListLoadingState.Success -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Colors.Base.regionListBackground)
                    ) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Colors.Base.regionListBackground
                        )
                        LazyColumn {
                            item {
                                CodeButton(
                                    onClick = onClickCode,
                                )
                            }
                            stickyHeader {
                                RegionListFilters(
                                    count = model.totalCount,
                                    isCheckBoxChecked = model.isCheckedUnregistered,
                                    onCheckedChange = { onChecked(model.isCheckedUnregistered) },
                                    isFilterOn = model.isFilter,
                                    onFilterClick = {
                                        shouldShowDialog = true
                                    }
                                )

                                if (shouldShowDialog) {
                                    RegionListFilterDialog(
                                        filterList = model.filterList,
                                        onDismissClick = {
                                            shouldShowDialog = model.isShowDialog
                                        },
                                        onFilterClearClick = {
                                            onFilterClearClick()
                                        },
                                        onFilterClick = {
                                            onFilterClick()
                                            shouldShowDialog = model.isShowDialog
                                        },
                                        onCheckedFilter = onCheckedFilter,
                                    )
                                }
                            }
                            item {
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = Colors.Base.regionListDivider
                                )
                                if (model.regionList.isEmpty()) {
                                    EmptyRegionList()
                                } else {
                                    RegionListWithPrefecture(
                                        regionListWithHeaderList = model.regionList,
                                        onClickRegionCardItem = onClickRegionCardItem,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
