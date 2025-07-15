package com.io.tea.android.ui.search.model

data class SearchTeaFilterModel(
    val id: Int,
    val prefecture: String,
    val text: String,
    var isChecked: Boolean = false
)