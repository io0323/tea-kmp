package com.io.tea.domain.repository

import com.io.tea.domain.api.MyApi
import com.io.tea.domain.api.TeaApi
import com.io.tea.domain.response.MyTeaResponse
import com.io.tea.domain.response.TeaResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TeaRepository : KoinComponent {
    private val TeaApi: TeaApi by inject()

    suspend fun fetchTea(): ApiResponse<TeaResponse> = handleApi {
        TeaApi.fetchTeaList()
    }
}
