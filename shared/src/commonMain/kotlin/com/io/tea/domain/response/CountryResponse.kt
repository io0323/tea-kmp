package com.io.tea.domain.response

import kotlinx.serialization.SerialName

data class CountryResponse(
    @SerialName("country_code_dto_list")
    val countryDtoList: List<CountryDTO>
) : BaseResponse

data class CountryDTO(
    val index: Int,
    val countryName: String,
    val countryCode: String,
)
