package com.io.tea.domain.usecase

import com.io.tea.domain.response.MyTeaDTO
import com.io.tea.domain.repository.ApiResponse
import com.io.tea.domain.repository.MyTeaRepository
import com.io.tea.domain.repository.TeaRepository
import com.io.tea.domain.response.TeaDTO
import org.koin.core.component.KoinComponent

/**
 * TeaListを取得するユースケース
 */
class FetchTeaListUseCase(
    private val TeaRepository: TeaRepository
) : KoinComponent {

    suspend operator fun invoke(): UseCaseResult<List<TeaDTO>> {
        return when (val response = TeaRepository.fetchTea()) {
            is ApiResponse.Success -> {
                val TeaList = response.data.TeaDtoList
                UseCaseResult.Success(TeaList)
            }

            is ApiResponse.Failure -> {
                UseCaseResult.Failure(response.errorMessage)
            }
        }
    }
}
