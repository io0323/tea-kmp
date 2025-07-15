package com.io.tea.android.ui.region.list.model

import com.io.tea.android.ui.region.list.state.RegionFilter

object RegionListConverter {
    fun convert(modelList: RegionFilter): RegionFilterModel {
        return RegionFilterModel(
            id = modelList.id,
            text = modelList.text,
            isChecked = modelList.isChecked
        )
    }

    fun convert(regionFilterList: List<RegionFilter>): List<RegionFilterModel> {
        val modelList = regionFilterList.map {
            RegionFilterModel(
                id = it.id,
                text = it.text,
                isChecked = it.isChecked
            )
        }
        return modelList
    }
}
