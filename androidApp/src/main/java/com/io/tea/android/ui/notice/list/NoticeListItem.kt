package com.io.tea.android.ui.notice.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.ui.notice.common.NoticeItem
import com.io.tea.android.ui.notice.common.NoticeModel

@Composable
internal fun NoticeListItem(
    notice: NoticeModel,
    modifier: Modifier = Modifier,
    onClick: (notice: NoticeModel) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(notice) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NoticeItem(
                notice = notice,
                modifier = modifier,
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = Colors.Grey300
        )
    }
}
