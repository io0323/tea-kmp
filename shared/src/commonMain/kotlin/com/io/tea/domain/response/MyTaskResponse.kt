package com.io.tea.domain.response

import kotlinx.serialization.SerialName

data class MyTeaResponse(
    @SerialName("my_Tea_dto_list")
    val myTeaDtoList: List<MyTeaDTO>
) : BaseResponse

data class MyTeaDTO(
    val imageURL: String,
    val imageDescription: String,
    val payName: String,
    val msg: String,
    val point: String,
    val statusColor: String,
)