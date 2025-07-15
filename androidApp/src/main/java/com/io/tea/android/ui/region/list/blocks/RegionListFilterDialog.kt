package com.io.tea.android.ui.region.list.blocks

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
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
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonPrimary
import com.io.tea.android.ui.common.component.ButtonSecondary
import com.io.tea.android.ui.region.list.model.RegionFilterModel

@Composable
fun RegionListFilterDialog(
    filterList: List<RegionFilterModel>,
    onDismissClick: () -> Unit,
    onFilterClearClick: () -> Unit,
    onFilterClick: () -> Unit,
    onCheckedFilter: (RegionFilterModel) -> Unit,
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    RegionListFilterDialogTitle()
                    Spacer(modifier = Modifier.height(8.dp))
                    RegionListFilterDialogPrefectures(
                        filterList = filterList,
                        modifier = Modifier.weight(1f),
                        onCheckedFilter = onCheckedFilter,
                    )
                    HorizontalDivider(
                        color = Colors.Base.regionListDivider,
                        thickness = 1.dp
                    )
                    RegionListFilterDialogButtons(
                        onFilterClearClick = onFilterClearClick,
                        onFilterClick = onFilterClick,
                    )
                }
            }
            RegionListFilterDialogCloseIcon(
                onClick = onDismissClick,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
private fun RegionListFilterDialogTitle() {
    Text(
        text = stringResource(R.string.region_list__filter_dialog_title),
        style = TeaAppTheme.typography.regionListFilterDialogTitle,
        modifier = Modifier.padding(top = 32.dp)
    )
}

@Composable
private fun RegionListFilterDialogPrefectures(
    filterList: List<RegionFilterModel>,
    modifier: Modifier,
    onCheckedFilter: (RegionFilterModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(filterList) { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .clickable { onCheckedFilter(item) }
                    .padding(
                        vertical = 8.dp,
                        horizontal = 24.dp
                    )
            ) {
                Checkbox(
                    checked = item.isChecked,
                    onCheckedChange = { onCheckedFilter(item) }
                )
                Text(
                    text = item.text,
                    style = TeaAppTheme.typography.regionListFilterDialogItem
                )
            }
            if (index < filterList.lastIndex) {
                HorizontalDivider(
                    color = Colors.Base.regionListDivider,
                    thickness = 1.dp,
                    modifier = modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
}

@Composable
private fun RegionListFilterDialogButtons(
    onFilterClearClick: () -> Unit,
    onFilterClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ButtonSecondary(
            isEnabled = true,
            textResId = R.string.region_list__filter_dialog_clear,
            width = 100.dp,
            onClick = onFilterClearClick
        )
        Spacer(modifier = Modifier.width(16.dp))
        ButtonPrimary(
            isEnabled = true,
            textResId = R.string.region_list__filter_dialog_filtering,
            buttonModifier = Modifier.weight(1f),
            onClick = onFilterClick
        )
    }
}

@Composable
private fun RegionListFilterDialogCloseIcon(
    onClick: () -> Unit,
    modifier: Modifier,
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
