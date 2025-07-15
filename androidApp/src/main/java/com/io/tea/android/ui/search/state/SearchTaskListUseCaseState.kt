package com.io.tea.android.ui.search.state

import com.io.tea.android.ui.search.model.SearchTeaFilterModel
import com.io.tea.android.ui.search.model.SearchTeaListModel
import com.io.tea.android.ui.search.model.SearchTeaSortModel

sealed class MyRegionListUseCaseState {
    data object Initial : MyRegionListUseCaseState()
    data object Loading : MyRegionListUseCaseState()
    data class Success(
        val myTeaList: List<SearchTeaListModel>,
        val myTeaSortList: List<SearchTeaSortModel>,
        val myTeaFilterMap: Map<String, List<SearchTeaFilterModel>>
    ) : MyRegionListUseCaseState()

    data class Failure(val errorMessage: String) : MyRegionListUseCaseState()
}

data class MyRegion(
    val imageURL: String,
    val imageDescription: String,
    val payName: String,
    val msg: String,
    val point: String,
    val regionColor: String,
)

data class MyRegionFilter(
    val id: Int,
    val prefecture: String,
    val text: String,
    var isChecked: Boolean = false
)
