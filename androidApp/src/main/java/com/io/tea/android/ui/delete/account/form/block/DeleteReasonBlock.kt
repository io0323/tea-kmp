package com.io.tea.android.ui.delete.account.form.block

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.delete.account.form.DeleteAccountFormViewModel
import com.io.tea.android.ui.delete.account.form.model.DeleteAccountItem
import com.io.tea.android.R.*
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TeaAppTextFieldLabel

@Composable
fun DeleteReasonBlock(
    viewModel: DeleteAccountFormViewModel,
    list: List<DeleteAccountItem>,
) {
    Column {
        TeaAppTextFieldLabel(
            resourceId = string.delete_account_form__reason,
            textStyle = TeaAppTheme.typography.h4,
        )
        Spacer(modifier = Modifier.height(8.dp))
        list.forEachIndexed { _, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable { viewModel.onCheckedDeleteReason(item) }
            ) {
                Checkbox(
                    checked = item.isChecked,
                    onCheckedChange = {
                        viewModel.onCheckedDeleteReason(item)
                    },
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = item.text,
                    color = Colors.FontPrimary,
                    style = TeaAppTheme.typography.h4,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
