package com.io.tea.domain.api

import com.io.tea.domain.response.MyTeaDTO
import com.io.tea.domain.response.MyTeaResponse

class MyApi(private val api: KtorApi) : KtorApi by api {

    suspend fun fetchMyTeaList(): MyTeaResponse {
        // TODO: テストデータを返す。
//            delay(2000L)
        return MyTeaResponse(myTeaDtoList = mockMyTeaList)

//        return client.post {
//            json()
//            apiUrl(RegionsEndPoints.NoticeList.url)
//        }.body()
    }

    private val mockMyTeaList = listOf(
        MyTeaDTO(
            imageURL = "dummyURL",
            imageDescription = "imageDescription",
            payName = "Tea One",
            msg = "◎Tea One MassageMassageMassageMassageMassageMassage",
            point = "1000000",
            statusColor = "#FF46B733"
        ),
        MyTeaDTO(
            imageURL = "dummyURL",
            imageDescription = "imageDescription",
            payName = "Tea Two",
            msg = "◎Tea Two MassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassageMassage",
            point = "1000000",
            statusColor = "#FF02A2D5"
        ),
    )
}
