package com.io.tea.domain.usecase

import com.io.tea.domain.repository.ApiResponse
import com.io.tea.domain.repository.CountryRepository
import com.io.tea.domain.response.CountryDTO
import org.koin.core.component.KoinComponent

/**
 * CountryListを取得するユースケース
 */
class FetchCountryListUseCase(
    private val countryRepository: CountryRepository
) : KoinComponent {

    suspend operator fun invoke(): UseCaseResult<List<CountryDTO>> {
        return when (val response = countryRepository.fetchCountry()) {
            is ApiResponse.Success -> {
                val TeaList = response.data.countryDtoList
                UseCaseResult.Success(TeaList)
            }

            is ApiResponse.Failure -> {
                UseCaseResult.Failure(response.errorMessage)
            }
        }
    }
}
