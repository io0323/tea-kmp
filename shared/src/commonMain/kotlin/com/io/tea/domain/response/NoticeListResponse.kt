package com.io.tea.domain.response

import kotlinx.serialization.SerialName

data class NoticeListResponse(
    @SerialName("notice_dto_list")
    val noticeDtoList: List<NoticeDTO>
) : BaseResponse

data class NoticeDTO(
    val date: String,
    val TeaName: String,
    val msg: String,
    val url: String,
    val colorCode: String,
)
