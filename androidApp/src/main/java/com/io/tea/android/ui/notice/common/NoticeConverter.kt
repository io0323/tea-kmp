package com.io.tea.android.ui.notice.common

import com.io.tea.android.ui.notice.detail.model.NoticeDetailModel
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.DateUtil.toFormat
import com.io.tea.domain.response.NoticeDTO

object NoticeConverter {
    fun convert(model: NoticeDTO): NoticeModel {
        return NoticeModel(
            regionColor = ColorUtil.hexToColor(model.colorCode),
            payName = model.TeaName,
            msg = model.msg,
            url = model.url,
            date = toFormat(
                date = model.date,
                pattern = "yyyy年M月d日"
            ),
        )
    }

    fun convert(model: NoticeDTO, message: String): NoticeDetailModel {
        return NoticeDetailModel(
            noticeModel = NoticeModel(
                regionColor = ColorUtil.hexToColor(model.colorCode),
                payName = model.TeaName,
                msg = model.msg,
                date = toFormat(
                    date = model.date,
                    pattern = "yyyy年M月d日"
                ),
                url = model.url,
            ),
            message = message,
        )
    }
}