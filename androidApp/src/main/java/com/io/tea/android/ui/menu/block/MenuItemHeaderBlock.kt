package com.io.tea.android.ui.menu.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TeaAppHorizontalDivider

@Composable
internal fun MenuItemHeaderBlock(
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color = Colors.Grey100),
    ) {
        Text(
            text = text,
            style = TeaAppTheme.typography.labelL,
            color = Colors.FontPrimary,
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp, start = 24.dp)
        )
    }
    TeaAppHorizontalDivider()
}