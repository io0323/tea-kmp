package com.io.tea.domain.api

import com.io.tea.domain.response.RegionDTO
import com.io.tea.domain.response.RegionListResponse
import kotlinx.coroutines.delay

class RegionsApi(private val api: KtorApi) : KtorApi by api {

    suspend fun fetchRegionList(): RegionListResponse {

        // TODO: テストデータを返す。
        delay(2000L)
        return RegionListResponse(regionDtoList = mockRegionList)

//        return client.post {
//            json()
//            apiUrl(RegionsEndPoints.RegionList.url)
//        }.body()
    }

    suspend fun fetchRegionDetails(regionId: Long): RegionDTO {
        // TODO: テストデータを返す。
        delay(2000L)
        return mockRegionList.find { it.id == regionId } ?: mockRegionList[0]
    }

    private val mockRegionList = listOf(
        RegionDTO(
            id = 1,
            name = "Tea One",
            description = "お得お得お得お得お得お得お得お得",
            image = "https://dummy.com/img/tea/1.jpg",
            isAdded = true,
            prefecture = "Tea One"
        ),
        RegionDTO(
            id = 4,
            name = "Tea Two",
            description = "お得お得お得お得お得お得お得お得",
            image = "https://dummy.com/img/tea/1.jpg",
            isAdded = false,
            prefecture = "Tea Two"
        ),
        RegionDTO(
            id = 23,
            name = "Tea Three",
            description = "お得お得お得お得お得お得お得お得",
            image = "https://dummy.com/img/tea/1.jpg",
            isAdded = true,
            prefecture = "Tea Three"
        ),
        RegionDTO(
            id = 8,
            name = "Tea Four",
            description = "お得お得お得お得お得お得お得お得",
            image = "https://dummy.com/img/tea/1.jpg",
            isAdded = true,
            prefecture = "Tea Four"
        ),
        RegionDTO(
            id = 9,
            name = "Tea Five",
            description = "お得お得お得お得お得お得お得お得",
            image = "https://dummy.com/img/tea/1.jpg",
            isAdded = false,
            prefecture = "Tea Five"
        ),
        RegionDTO(
            id = 10,
            name = "Tea Six",
            description = "お得お得お得お得お得お得お得お得",
            image = "https://dummy.com/img/tea/1.jpg",
            isAdded = true,
            prefecture = "Tea Six"
        ),
    )
}

enum class RegionsEndPoints(val url: String) {
    RegionList("/appli_api/v2/tea.php")
}
