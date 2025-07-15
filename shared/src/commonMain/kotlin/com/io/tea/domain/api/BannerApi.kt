package com.io.tea.domain.api

import com.io.tea.domain.response.BannerDTO
import com.io.tea.domain.response.BannerResponse

class BannerApi(private val api: KtorApi) : KtorApi by api {

    suspend fun fetchBannerList(): BannerResponse {
        // TODO: テストデータを返す。
//            delay(2000L)
        return BannerResponse(bannerDtoList = mockBannerList)

//        return client.post {
//            json()
//            apiUrl(RegionsEndPoints.NoticeList.url)
//        }.body()
    }

    private val mockBannerList = listOf(
        BannerDTO(
            imageURL = "hangryangry",
            imageDescription = "imageDescription",
            bannerName = "AAAバナー",
            msg = "AAAバナー MSG",
            point = "1000000",
            statusColor = "#FF46B733"
        ),
        BannerDTO(
            imageURL = "hangryangry",
            imageDescription = "imageDescription",
            bannerName = "BBBバナー",
            msg = "BBBバナー MSG",
            point = "1000000",
            statusColor = "#FF02A2D5"
        ),
    )
}
