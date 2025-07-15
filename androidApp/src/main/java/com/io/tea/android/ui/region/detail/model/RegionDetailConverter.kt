package com.io.tea.android.ui.region.detail.model

import com.io.tea.android.ui.region.detail.state.RegionDetail
import com.io.tea.android.util.DateUtil

object RegionDetailConverter {
    fun convert(model: RegionDetail): RegionDetailModel {
        return RegionDetailModel(
            regionId = model.regionId,
            imageUrl = model.imageUrl,
            description = model.description,
            headerTitle = model.headerTitle,
            campaignName = model.campaignName,
            campaignDetail = model.campaignDetail,
            campaignPeriodStart = DateUtil.toFormatYMD(
                date = model.campaignPeriodStart,
                pattern = "yyyy/MM/dd"
            ),
            campaignPeriodEnd = DateUtil.toFormatYMD(
                date = model.campaignPeriodEnd,
                pattern = "yyyy/MM/dd"
            ),
            pointExpirationStart = DateUtil.toFormatYMD(
                date = model.pointExpirationStart,
                pattern = "yyyy年MM月dd日"
            ),
            pointExpirationEnd = DateUtil.toFormatYMD(
                date = model.pointExpirationEnd,
                pattern = "yyyy年MM月dd日"
            ),
            pointExpirationNote = model.pointExpirationNote,
            campaignSiteName = model.campaignSiteName,
            campaignSiteURL = model.campaignSiteURL,
            tosTitle = model.tosTitle,
            tosText = model.tosText,
        )
    }
}
