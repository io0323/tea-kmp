package com.io.tea.android.ui.common.component.model.converter

import com.io.tea.android.ui.search.model.SearchTeaListModel
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.StringUtil
import com.io.tea.domain.response.MyTeaDTO

object MyTeaConverter {
    fun convert(dto: MyTeaDTO): SearchTeaListModel {
        return SearchTeaListModel(
            imageURL = dto.imageURL,
            imageDescription = dto.imageDescription,
            payName = dto.payName,
            msg = dto.msg,
            point = StringUtil.formatComma(dto.point.toLong()),
            statusColor = ColorUtil.hexToColor(dto.statusColor),
        )
    }
}
