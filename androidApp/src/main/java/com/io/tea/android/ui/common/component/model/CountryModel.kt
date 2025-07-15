package com.io.tea.android.ui.common.component.model

data class CountryModel(
    val index: Int,
    val countryName: String,
    val countryCode: String,
) {
    companion object {
        val default = listOf(
            CountryModel(
                index = 0,
                countryName = "",
                countryCode = "",
            )
        )
    }
}
