package com.io.tea.domain.api

import com.io.tea.domain.response.CountryDTO
import com.io.tea.domain.response.CountryResponse

class AccountApi(private val api: KtorApi) : KtorApi by api {

    suspend fun fetchCountryList(): CountryResponse {
        // TODO: テストデータを返す。
//            delay(2000L)
        return CountryResponse(countryDtoList = mockCountryList)

//        return client.post {
//            json()
//            apiUrl(RegionsEndPoints.NoticeList.url)
//        }.body()
    }

    private val mockCountryList = listOf(
        CountryDTO(
            index = 0,
            countryName = "日本",
            countryCode = "(+81)",
        ),
        CountryDTO(
            index = 1,
            countryName = "中国",
            countryCode = "(+86)",
        ),
        CountryDTO(
            index = 2,
            countryName = "香港",
            countryCode = "(+852)",
        ),
        CountryDTO(
            index = 3,
            countryName = "韓国",
            countryCode = "(+886)",
        ),
        CountryDTO(
            index = 4,
            countryName = "台湾",
            countryCode = "(+86)",
        ),
        CountryDTO(
            index = 5,
            countryName = "オーストラリア",
            countryCode = "(+61)",
        ),
        CountryDTO(
            index = 6,
            countryName = "カナダ",
            countryCode = "(+1)",
        ),
        CountryDTO(
            index = 7,
            countryName = "イギリス",
            countryCode = "(+44)",
        ),
        CountryDTO(
            index = 8,
            countryName = "アメリカ",
            countryCode = "(+1)",
        ),
    )
}
