package com.io.tea.domain.repository

import com.io.tea.domain.api.BannerApi
import com.io.tea.domain.api.MyApi
import com.io.tea.domain.api.TeaApi
import com.io.tea.domain.response.BannerResponse
import com.io.tea.domain.response.MyTeaResponse
import com.io.tea.domain.response.TeaResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BannerRepository : KoinComponent {
    private val bannerApi: BannerApi by inject()

    suspend fun fetchTea(): ApiResponse<BannerResponse> = handleApi {
        bannerApi.fetchBannerList()
    }
}
