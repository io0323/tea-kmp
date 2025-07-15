package com.io.tea.android.ui.search.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.search.SearchTeaListViewModel
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.search.model.SearchTeaSortModel

@Composable
fun MyRegionListSortItem(
    viewModel: SearchTeaListViewModel,
    model: SearchTeaSortModel,
) {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable { viewModel.onSelectedSort(model) }
    ) {
        RadioButton(
            selected = model.isSelected,
            onClick = {
                viewModel.onSelectedSort(model)
            },
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = model.text,
            color = Colors.FontPrimary,
            style = TeaAppTheme.typography.h4,
            modifier = Modifier.padding(start = 18.dp)
        )
    }
}
