package com.io.tea.domain.api

import com.io.tea.domain.response.TeaDTO
import com.io.tea.domain.response.TeaResponse

class TeaApi(private val api: KtorApi) : KtorApi by api {

    suspend fun fetchTeaList(): TeaResponse {
        // TODO: テストデータを返す。
//            delay(2000L)
        return TeaResponse(TeaDtoList = mockTeaList)

//        return client.post {
//            json()
//            apiUrl(RegionsEndPoints.NoticeList.url)
//        }.body()
    }

    private val mockTeaList = listOf(
        TeaDTO(
            imageURL = "hangryangry",
            imageDescription = "imageDescription",
            TeaName = "AAAロット",
            msg = "AAAロット MSG",
            detail = "AAAロット 10000",
            statusColor = "#FF46B733"
        ),
        TeaDTO(
            imageURL = "hangryangry",
            imageDescription = "imageDescription",
            TeaName = "BBBロット",
            msg = "BBBロット MSG",
            detail = "BBBロット 20000",
            statusColor = "#FF02A2D5"
        ),
    )
}
