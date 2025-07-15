package com.io.tea.android.ui.delete.account.confirm.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun DeleteReasonBlock(
    list: List<String>,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = TeaAppTheme.colors.Grey50,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(10.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.delete_account_confirm__reason),
                color = TeaAppTheme.colors.FontSecondary,
                style = TeaAppTheme.typography.label
            )
            Spacer(modifier = Modifier.height(8.dp))
            list.forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "・$it",
                        color = TeaAppTheme.colors.FontPrimary,
                        style = TeaAppTheme.typography.h4,
                    )
                }
            }
        }
    }
}
