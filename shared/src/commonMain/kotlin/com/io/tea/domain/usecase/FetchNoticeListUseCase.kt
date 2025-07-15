package com.io.tea.domain.usecase

import com.io.tea.domain.response.NoticeDTO
import com.io.tea.domain.repository.ApiResponse
import com.io.tea.domain.repository.NoticeRepository
import org.koin.core.component.KoinComponent

/**
 * お知らせを取得するユースケース
 */
class FetchNoticeListUseCase(
    private val noticeRepository: NoticeRepository
) : KoinComponent {

    suspend operator fun invoke(): UseCaseResult<List<NoticeDTO>> {
        return when (val response = noticeRepository.fetchNoticeList()) {
            is ApiResponse.Success -> {
                val noticeList = response.data.noticeDtoList
                UseCaseResult.Success(noticeList)
            }

            is ApiResponse.Failure -> {
                UseCaseResult.Failure(response.errorMessage)
            }
        }
    }
}
