package com.io.tea.domain.usecase

import com.io.tea.domain.response.RegionDTO
import com.io.tea.domain.repository.ApiResponse
import com.io.tea.domain.repository.RegionsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchRegionDetailsUseCase : KoinComponent {
    private val regionsRepository: RegionsRepository by inject()

    suspend operator fun invoke(regionId: Long): UseCaseResult<RegionDTO> {
        return when (val response = regionsRepository.fetchRegionDetails(regionId = regionId)) {
            is ApiResponse.Success -> {
                UseCaseResult.Success(response.data)
            }

            is ApiResponse.Failure -> {
                UseCaseResult.Failure(response.errorMessage)
            }
        }
    }
}
