package com.io.tea.android.ui.notice.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun NoticeItem(
    notice: NoticeModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "contentdescription",
                tint = notice.regionColor,
                modifier = Modifier.size(8.dp),
            )
            Text(
                text = notice.payName,
                style = TeaAppTheme.typography.label,
                color = Colors.Font.second,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = notice.msg,
            style = TeaAppTheme.typography.h4,
            color = Colors.Font.primary,
            softWrap = true,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = notice.date,
            style = TeaAppTheme.typography.caption,
            color = Colors.Font.primary,
            maxLines = 1,
            softWrap = true,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}