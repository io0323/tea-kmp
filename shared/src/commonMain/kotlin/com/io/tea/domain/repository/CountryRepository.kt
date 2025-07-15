package com.io.tea.domain.repository

import com.io.tea.domain.api.AccountApi
import com.io.tea.domain.response.CountryResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CountryRepository : KoinComponent {
    private val accountApi: AccountApi by inject()

    suspend fun fetchCountry(): ApiResponse<CountryResponse> = handleApi {
        accountApi.fetchCountryList()
    }
}
