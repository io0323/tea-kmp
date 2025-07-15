package com.io.tea.domain.repository

import com.io.tea.domain.api.RegionsApi
import com.io.tea.domain.response.RegionDTO
import com.io.tea.domain.response.RegionListResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegionsRepository : KoinComponent {
    private val regionsApi: RegionsApi by inject()

    suspend fun fetchRegionList(): ApiResponse<RegionListResponse> = handleApi {
        regionsApi.fetchRegionList()
    }

    suspend fun fetchRegionDetails(regionId: Long): ApiResponse<RegionDTO> = handleApi {
        regionsApi.fetchRegionDetails(regionId = regionId)
    }
}
