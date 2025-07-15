package com.io.tea.domain.repository

import com.io.tea.domain.api.MyApi
import com.io.tea.domain.response.MyTeaResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MyTeaRepository : KoinComponent {
    private val myApi: MyApi by inject()

    suspend fun fetchMyTea(): ApiResponse<MyTeaResponse> = handleApi {
        myApi.fetchMyTeaList()
    }
}
