package com.io.tea.android.ui.region.list.model

import com.io.tea.domain.usecase.RegionListWithHeader

data class RegionListModel(
    val regionList: List<RegionListWithHeader>,
    val filterList: List<RegionFilterModel>,
    val isFilter: Boolean,
    val isShowDialog: Boolean,
    val isCheckedUnregistered: Boolean,
    val totalCount: Int,
    val isEnableTeaAppDelete: Boolean,
) {
    companion object {
        val default: RegionListModel = RegionListModel(
            regionList = emptyList(),
            filterList = emptyList(),
            isFilter = false,
            isShowDialog = false,
            isCheckedUnregistered = false,
            totalCount = 0,
            isEnableTeaAppDelete = false,
        )
    }
}

data class RegionFilterModel(
    val id: Int,
    val text: String,
    var isChecked: Boolean = false,
)
