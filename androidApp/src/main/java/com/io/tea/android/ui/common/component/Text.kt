package com.io.tea.android.ui.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.util.TextUtil

@Composable
fun CheckboxWithLink(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    url: String,
    linkedText: String,
    onLinkClick: () -> Unit,
    suffix: String = "",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 41.dp)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null,
            modifier = Modifier.clickable {
                onCheckedChange(!checked)
            }
        )

        TextUtil.LinkText(
            text = "$linkedText$suffix",
            linkedText = linkedText,
            link = url,
            textFontStyle = TeaAppTheme.typography.h6,
            linkFontStyle = TeaAppTheme.typography.h5,
            onClick = onLinkClick
        )
    }
}
