package com.io.tea.android.ui.common.component.model.converter

import com.io.tea.android.ui.common.component.model.CountryModel
import com.io.tea.domain.response.CountryDTO

object CountryConverter {
    fun convert(dto: CountryDTO): CountryModel {
        return CountryModel(
            index = dto.index,
            countryName = dto.countryName,
            countryCode = dto.countryCode,
        )
    }
}
