package com.io.tea.android.ui.search.state

import com.io.tea.android.ui.common.component.model.RegionCardModel


internal data class SearchTeaListViewModelUiState(
    val myRegionList: List<RegionCardModel>,
    val isLoad: Boolean,
    val isRefresh: Boolean,
    val isError: Boolean,
)
