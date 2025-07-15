package com.io.tea.android.ui.common.component.model.converter

import com.io.tea.android.MainApplication
import com.io.tea.android.R
import com.io.tea.android.ui.home.model.TeaModel
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.ImageUtil
import com.io.tea.domain.response.TeaDTO

object TeaConverter {
    fun convert(dto: TeaDTO): TeaModel {

        return TeaModel(
            // TODO : サーバーのPathを指定する
            imageURL = ImageUtil.getDrawablePath(
                context = MainApplication.appContext,
                drawablePath = "${R.drawable.hangryangry}"
            ),
            imageDescription = dto.imageDescription,
            teaName = dto.TeaName,
            msg = dto.msg,
            detail = dto.detail,
            statusColor = ColorUtil.hexToColor(dto.statusColor),
        )
    }
}
