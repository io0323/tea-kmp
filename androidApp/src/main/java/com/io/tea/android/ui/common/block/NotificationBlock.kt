package com.io.tea.android.ui.common.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonQuaternary
import com.io.tea.android.ui.common.component.model.ButtonIconType
import com.io.tea.android.ui.common.component.model.NotificationModel
import com.io.tea.android.ui.notice.common.NoticeModel
import com.io.tea.android.ui.notice.list.NoticeListItem

@Composable
internal fun NotificationBlock(
    notificationModel: NotificationModel,
    onDetailClick: () -> Unit,
    onNoticeItemClick: (NoticeModel) -> Unit
) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(TeaAppTheme.colors.Grey50)
                .padding(horizontal = 24.dp, vertical = 5.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "icon",
                tint = TeaAppTheme.colors.BlueDark,
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "お知らせ",
                style = TeaAppTheme.typography.h3,
            )

            Spacer(modifier = Modifier.weight(1f))
            ButtonQuaternary(
                isEnabled = true,
                textResId = R.string.region_home__notice_list,
                textStyle = TeaAppTheme.typography.btn,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                iconType = ButtonIconType.RIGHT,
                onClick = onDetailClick,
            )
        }

        notificationModel.noticeList.forEach { notice ->
            NoticeListItem(
                notice = notice,
                onClick = {
                    onNoticeItemClick(notice)
                },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}
