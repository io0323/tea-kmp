package com.io.tea.android.ui.search.model

import com.io.tea.android.ui.search.state.MyRegionFilter

object SearchTeaListConverter {
    fun convert(modelList: MyRegionFilter): SearchTeaFilterModel {
        return SearchTeaFilterModel(
            id = modelList.id,
            prefecture = modelList.prefecture,
            text = modelList.text,
            isChecked = modelList.isChecked
        )
    }

    fun convert(myRegionFilterList: List<MyRegionFilter>): Map<String, List<SearchTeaFilterModel>> {
        val modelList = myRegionFilterList.map {
            SearchTeaFilterModel(
                id = it.id,
                prefecture = it.prefecture,
                text = it.text,
                isChecked = it.isChecked
            )
        }
        return modelList.groupBy { it.prefecture }
    }
}
