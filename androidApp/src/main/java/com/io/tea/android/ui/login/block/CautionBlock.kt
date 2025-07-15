package com.io.tea.android.ui.login.block

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun CautionBlock(
    list: List<Int>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        list.forEach {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Text(
                    text = "・",
                    color = TeaAppTheme.colors.Error,
                    style = TeaAppTheme.typography.labelL,
                )
                Text(
                    text = stringResource(id = it),
                    color = TeaAppTheme.colors.Error,
                    style = TeaAppTheme.typography.labelL,
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
