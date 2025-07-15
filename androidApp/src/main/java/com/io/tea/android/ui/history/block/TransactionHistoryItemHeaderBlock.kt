package com.io.tea.android.ui.history.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun TransactionHistoryItemHeaderBlock(headerYearMonth: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(TeaAppTheme.colors.Grey100)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = headerYearMonth,
            textAlign = TextAlign.Left,
            style = TeaAppTheme.typography.label,
            color = TeaAppTheme.colors.FontPrimary,
        )
    }
}