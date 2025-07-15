package com.io.tea.android.ui.menu.block.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TeaAppHorizontalDivider
import com.io.tea.android.ui.common.component.ChevronRight

@Composable
internal fun SettingListItemRow(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Colors.FontPrimary,
    textStyle: TextStyle = TeaAppTheme.typography.h4,
    subText: String? = null,
    subTextColor: Color = Colors.FontSecondary,
    subTextStyle: TextStyle = TeaAppTheme.typography.h6,
    onClick: (() -> Unit)? = null,
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(enabled = onClick != null) { onClick?.invoke() }
                .background(color = Colors.White)
                .padding(vertical = 15.5.dp, horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = textStyle,
                color = textColor,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )
            if (subText != null) {
                Text(
                    text = subText,
                    style = subTextStyle,
                    color = subTextColor,
                    textAlign = TextAlign.End,
                )
            }
            ChevronRight()
        }
        TeaAppHorizontalDivider()
    }
}
