package com.io.tea.android.ui.common.component.model.converter

import com.io.tea.android.MainApplication
import com.io.tea.android.R
import com.io.tea.android.ui.home.model.BannerModel
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.ImageUtil
import com.io.tea.android.util.StringUtil
import com.io.tea.domain.response.BannerDTO

object BannerConverter {
    fun convert(dto: BannerDTO): BannerModel {

        return BannerModel(
            // TODO : サーバーのPathを指定する
            imageURL = ImageUtil.getDrawablePath(
                context = MainApplication.appContext,
                drawablePath = "${R.drawable.hangryangry}"
            ),
            imageDescription = dto.imageDescription,
            bannerName = dto.bannerName,
            msg = dto.msg,
            point = StringUtil.formatComma(dto.point.toLong()),
            statusColor = ColorUtil.hexToColor(dto.statusColor),
        )
    }
}
