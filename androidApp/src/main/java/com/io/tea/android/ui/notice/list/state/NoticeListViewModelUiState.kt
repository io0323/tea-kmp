package com.io.tea.android.ui.notice.list.state

import com.io.tea.android.ui.notice.common.NoticeModel


internal data class NoticeListViewModelUiState(
    val noticeList: List<NoticeModel>,
    val isLoad: Boolean,
    val isRefresh: Boolean,
    val isError: Boolean,
)
