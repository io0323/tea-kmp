package com.io.tea.android.ui.search.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.io.tea.android.ui.search.SearchTeaListViewModel
import com.io.tea.android.ui.search.model.SearchTeaFilterModel
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonPrimary

@Composable
fun MyRegionListFilterDialog(
    viewModel: SearchTeaListViewModel,
    myRegionFilterMap: Map<String, List<SearchTeaFilterModel>>,
    onDismissClick: () -> Unit,
    onClickFilter: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    DialogTitle()
                    Spacer(modifier = Modifier.height(8.dp))
                    DialogMyRegionFilterWithHeaderListItem(
                        viewModel = viewModel,
                        myRegionFilterMap = myRegionFilterMap,
                        modifier = Modifier.weight(1f)
                    )
                    HorizontalDivider(
                        color = Colors.Base.regionListDivider,
                        thickness = 1.dp
                    )
                    DialogButton(
                        onClickFilter = onClickFilter
                    )
                }
            }
            DialogClose(
                onClick = onDismissClick,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
private fun DialogTitle() {
    Text(
        text = stringResource(R.string.button__filter),
        style = TeaAppTheme.typography.regionListFilterDialogTitle,
        modifier = Modifier.padding(top = 32.dp)
    )
}

@Composable
private fun DialogMyRegionFilterWithHeaderListItem(
    viewModel: SearchTeaListViewModel,
    myRegionFilterMap: Map<String, List<SearchTeaFilterModel>>,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        myRegionFilterMap.forEach { (prefecture, regionFilterModelList) ->
            item {
                MyRegionListFilterHeaderBlock(prefecture = prefecture)
            }
            item {
                DialogMyRegionFilterListItem(
                    viewModel = viewModel,
                    modelList = regionFilterModelList
                )
            }
        }
    }
}

@Composable
private fun DialogMyRegionFilterListItem(
    viewModel: SearchTeaListViewModel,
    modelList: List<SearchTeaFilterModel>,
) {
    modelList.map { model ->
        MyRegionListFilterItem(
            viewModel = viewModel,
            model = model,
        )
    }
}

@Composable
private fun DialogButton(
    onClickFilter: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        ButtonPrimary(
            isEnabled = true,
            textResId = R.string.my_region_list__submit,
            buttonModifier = Modifier.weight(1f),
            onClick = {
                onClickFilter()
            }
        )
    }
}

@Composable
private fun DialogClose(
    onClick: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .offset(x = (-12).dp, y = 12.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "Close",
            modifier = modifier
                .size(32.dp)
        )
    }
}
