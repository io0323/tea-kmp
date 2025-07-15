package com.io.tea.android.ui.common.component.model

import com.io.tea.android.ui.notice.common.NoticeModel

data class NotificationModel(
    val noticeList: List<NoticeModel>,
) {
    companion object {
        val default = NotificationModel(
            noticeList = emptyList(),
        )
    }
}