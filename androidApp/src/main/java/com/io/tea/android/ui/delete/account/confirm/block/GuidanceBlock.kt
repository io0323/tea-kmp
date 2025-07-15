package com.io.tea.android.ui.delete.account.confirm.block

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun GuidanceBlock() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.delete_account_confirm__guidance_title),
            color = TeaAppTheme.colors.FontPrimary,
            style = TeaAppTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = stringResource(id = R.string.delete_account_confirm__guidance_text),
            color = TeaAppTheme.colors.FontPrimary,
            style = TeaAppTheme.typography.h6
        )
    }
}