package com.io.tea.android.ui.delete.account.auth.block

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun CautionBlock(
    list: List<Int>,
) {
    Column {
        list.forEach {
            Row(
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    text = "・",
                    color = TeaAppTheme.colors.Error,
                    style = TeaAppTheme.typography.caption,
                )
                Text(
                    text = stringResource(id = it),
                    color = TeaAppTheme.colors.Error,
                    style = TeaAppTheme.typography.caption,
                )
            }
        }
    }
}
