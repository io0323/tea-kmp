package com.io.tea.android.ui.search.dialog

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
internal fun MyRegionListFilterHeaderBlock(prefecture: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .padding(horizontal = 24.dp)
            .background(TeaAppTheme.colors.Grey100)
    ) {
        Text(
            text = prefecture,
            textAlign = TextAlign.Left,
            style = TeaAppTheme.typography.label,
            color = TeaAppTheme.colors.FontPrimary,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}