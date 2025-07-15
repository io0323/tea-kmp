package com.io.tea.domain.response

import kotlinx.serialization.SerialName

data class TeaResponse(
    @SerialName("Tea_dto_list")
    val TeaDtoList: List<TeaDTO>
) : BaseResponse

data class TeaDTO(
    val imageURL: String,
    val imageDescription: String,
    val TeaName: String,
    val msg: String,
    val detail: String,
    val statusColor: String,
)
