package com.io.tea.android.ui.common.block

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.util.TextUtil

@Composable
internal fun SmsNotReceivedLink(
    text: String,
    linkedText: String,
    link: String,
    onLinkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextUtil.LinkText(
        text = text,
        linkedText = linkedText,
        link = link,
        textFontStyle = TeaAppTheme.typography.label,
        linkFontStyle = TeaAppTheme.typography.label,
        onClick = onLinkClick,
        modifier = modifier
    )
}
