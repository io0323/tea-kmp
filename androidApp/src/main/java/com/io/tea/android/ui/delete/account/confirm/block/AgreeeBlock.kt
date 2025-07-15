package com.io.tea.android.ui.delete.account.confirm.block

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.delete.account.confirm.DeleteAccountConfirmViewModel
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun AgreeBlock(
    viewModel: DeleteAccountConfirmViewModel,
    isChecked: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        //
        modifier = Modifier
            .padding(start = 27.dp)
            .clickable { viewModel.onCheckedDeleteAgree(isChecked) }
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                viewModel.onCheckedDeleteAgree(isChecked)
            },
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = stringResource(R.string.delete_account_confirm__agree),
            color = TeaAppTheme.colors.FontPrimary,
            style = TeaAppTheme.typography.h6,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}