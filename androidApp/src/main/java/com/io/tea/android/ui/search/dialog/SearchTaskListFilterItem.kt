package com.io.tea.android.ui.search.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.search.SearchTeaListViewModel
import com.io.tea.android.ui.search.model.SearchTeaFilterModel
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun MyRegionListFilterItem(
    viewModel: SearchTeaListViewModel, // TODO : 本実装時は直接ViewModelを渡さない
    model: SearchTeaFilterModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .clickable { viewModel.onCheckedFilter(model) }
            .padding(horizontal = 24.dp)
    ) {
        Checkbox(
            checked = model.isChecked,
            onCheckedChange = { viewModel.onCheckedFilter(model) }
        )
        Text(
            text = model.text,
            style = TeaAppTheme.typography.regionListFilterDialogItem
        )
    }
}
