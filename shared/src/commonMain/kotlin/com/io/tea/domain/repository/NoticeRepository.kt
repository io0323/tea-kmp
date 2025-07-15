package com.io.tea.domain.repository

import com.io.tea.domain.api.NoticeApi
import com.io.tea.domain.response.NoticeListResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoticeRepository : KoinComponent {
    private val noticeApi: NoticeApi by inject()

    suspend fun fetchNoticeList(): ApiResponse<NoticeListResponse> = handleApi {
        noticeApi.fetchNoticeList()
    }
}
