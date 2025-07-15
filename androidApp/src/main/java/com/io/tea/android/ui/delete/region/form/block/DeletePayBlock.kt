package com.io.tea.android.ui.delete.region.form.block

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.delete.region.form.DeleteRegionFormViewModel
import com.io.tea.android.ui.delete.region.form.model.DeletePayItem
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TeaAppTextFieldLabel

@Composable
fun DeletePayBlock(
    viewModel: DeleteRegionFormViewModel,
    list: List<DeletePayItem>
) {
    Column {
        TeaAppTextFieldLabel(
            resourceId = R.string.delete_region_form__select_pay,
            textStyle = TeaAppTheme.typography.h4,
        )
        Spacer(modifier = Modifier.height(9.dp))
        list.mapIndexed { _, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable { viewModel.onSelectedDeletePay(item) }
            ) {
                RadioButton(
                    selected = item.isSelected,
                    onClick = {
                        viewModel.onSelectedDeletePay(item)
                    },
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = item.text,
                    color = Colors.FontPrimary,
                    style = TeaAppTheme.typography.h4,
                    modifier = Modifier.padding(start = 18.dp)
                )
            }
        }
    }
}