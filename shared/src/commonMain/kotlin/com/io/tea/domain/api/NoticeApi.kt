package com.io.tea.domain.api

import com.io.tea.domain.response.NoticeDTO
import com.io.tea.domain.response.NoticeListResponse

class NoticeApi(private val api: KtorApi) : KtorApi by api {
    suspend fun fetchNoticeList(): NoticeListResponse {

        // TODO: テストデータを返す。
//        delay(2000L)
        return NoticeListResponse(noticeDtoList = mockNoticeList)

//        return client.post {
//            json()
//            apiUrl(RegionsEndPoints.NoticeList.url)
//        }.body()
    }

    private val mockNoticeList = listOf(
        NoticeDTO(
            date = "2023年1月10日",
            TeaName = "Noticeロット 1",
            msg = "Noticeロット 1 MSG",
            url = "hangryangry",
            colorCode = "#FF0000FF",
        ),
        NoticeDTO(
            date = "2023年1月10日",
            TeaName = "Noticeロット 2",
            msg = "Noticeロット 2 MSG",
            url = "hangryangry",
            colorCode = "#FF0000FF",
        ),
        NoticeDTO(
            date = "2023年1月10日",
            TeaName = "Noticeロット 3",
            msg = "Noticeロット 3 MSG",
            url = "hangryangry",
            colorCode = "#FF0000FF",
        ),
        NoticeDTO(
            date = "2023年1月10日",
            TeaName = "Noticeロット 4",
            msg = "Noticeロット 4 MSG",
            url = "hangryangry",
            colorCode = "#FF62686B",
        ),
        NoticeDTO(
            date = "2023年1月10日",
            TeaName = "Noticeロット 5",
            msg = "Noticeロット 5 MSG",
            url = "hangryangry",
            colorCode = "#FF0000FF",
        ),
        NoticeDTO(
            date = "2023年1月10日",
            TeaName = "Noticeロット 6",
            msg = "Noticeロット 6 MSG",
            url = "hangryangry",
            colorCode = "#FF0000FF",
        ),
    )
}
