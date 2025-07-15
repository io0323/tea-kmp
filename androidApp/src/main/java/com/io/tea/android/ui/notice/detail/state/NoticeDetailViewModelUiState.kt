package com.io.tea.android.ui.notice.detail.state

import com.io.tea.android.ui.notice.detail.model.NoticeDetailModel

internal data class NoticeDetailViewModelUiState(
    val noticeList: List<NoticeDetailModel>,
    val isLoad: Boolean,
    val isRefresh: Boolean,
    val isError: Boolean,
)
