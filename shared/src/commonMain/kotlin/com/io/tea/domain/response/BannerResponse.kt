package com.io.tea.domain.response

import kotlinx.serialization.SerialName

data class BannerResponse(
    @SerialName("banner_dto_list")
    val bannerDtoList: List<BannerDTO>
) : BaseResponse

data class BannerDTO(
    val imageURL: String,
    val imageDescription: String,
    val bannerName: String,
    val msg: String,
    val point: String,
    val statusColor: String,
)