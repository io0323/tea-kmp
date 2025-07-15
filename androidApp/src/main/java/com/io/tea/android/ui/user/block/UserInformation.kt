package com.io.tea.android.ui.user.block

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun UserInformation(value: String) {
    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Text(
                text = value,
                color = Colors.FontPrimary,
                style = TeaAppTheme.typography.h4
            )
        }
        HorizontalDivider(color = Colors.Grey300)
    }
}
