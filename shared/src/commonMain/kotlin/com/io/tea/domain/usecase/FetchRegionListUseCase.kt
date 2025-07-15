package com.io.tea.domain.usecase

import com.io.tea.domain.response.RegionDTO
import com.io.tea.domain.repository.ApiResponse
import com.io.tea.domain.repository.RegionsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Lot一覧を取得するユースケース
 */
class FetchRegionListUseCase : KoinComponent {
    private val regionsRepository: RegionsRepository by inject()

    suspend operator fun invoke(): UseCaseResult<List<RegionListWithHeader>> {
        return when (val response = regionsRepository.fetchRegionList()) {
            is ApiResponse.Success -> {
                val regionList = response.data.regionDtoList
                val regionListWithHeader = convertRegionList(regionList)
                UseCaseResult.Success(regionListWithHeader)
            }

            is ApiResponse.Failure -> {
                UseCaseResult.Failure(response.errorMessage)
            }
        }
    }

    private fun convertRegionList(regionList: List<RegionDTO>): List<RegionListWithHeader> {
        // 都道府県でグループ化して、RegionListに変換
        return regionList
            .groupBy { it.prefecture }
            .map { RegionListWithHeader(headerName = it.key, regionList = it.value) }
            .toList()
    }
}

sealed class UseCaseResult<out T> {
    data class Success<out T>(val data: T) : UseCaseResult<T>()
    data class Failure(val errorMessage: String) : UseCaseResult<Nothing>()
}

data class RegionListWithHeader(
    val headerName: String,
    val regionList: List<RegionDTO>
)

