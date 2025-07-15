package com.io.tea.domain.usecase

import com.io.tea.domain.repository.ApiResponse
import com.io.tea.domain.repository.BannerRepository
import com.io.tea.domain.response.BannerDTO
import org.koin.core.component.KoinComponent

/**
 * BannerListを取得するユースケース
 */
class FetchBannerListUseCase(
    private val bannerRepository: BannerRepository
) : KoinComponent {

    suspend operator fun invoke(): UseCaseResult<List<BannerDTO>> {
        return when (val response = bannerRepository.fetchTea()) {
            is ApiResponse.Success -> {
                val bannerList = response.data.bannerDtoList
                UseCaseResult.Success(bannerList)
            }

            is ApiResponse.Failure -> {
                UseCaseResult.Failure(response.errorMessage)
            }
        }
    }
}
