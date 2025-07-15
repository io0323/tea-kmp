package com.io.tea.android.ui.menu.block.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.resource.theme.SwitchTheme
import com.io.tea.android.ui.common.TeaAppHorizontalDivider
import com.io.tea.android.ui.common.component.Switch

@Composable
internal fun SettingListItemRowWithSwitch(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(vertical = 15.5.dp, horizontal = 24.dp),
    isChecked: Boolean = false,
    text: String,
    textColor: Color = Colors.FontPrimary,
    textStyle: TextStyle = TeaAppTheme.typography.h4,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Colors.White)
                .padding(padding),
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
            Switch(
                colors = SwitchTheme.colors,
                isChecked = isChecked,
                onCheckedChange = onCheckedChange,
            )
        }
        TeaAppHorizontalDivider()
    }
}