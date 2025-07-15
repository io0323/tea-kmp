package com.io.tea.domain.response

import kotlinx.serialization.SerialName

interface BaseResponse

data class RegionListResponse(
    @SerialName("region_dto_list")
    val regionDtoList: List<RegionDTO>
) : BaseResponse

data class RegionDTO(
    val id: Long,
    @SerialName("region_name")
    val name: String,
    val description: String,
    val image: String,
    val isAdded: Boolean,
    @SerialName("todofuken")
    val prefecture: String,
)
