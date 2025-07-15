package com.io.tea.domain.usecase

import com.io.tea.domain.response.MyTeaDTO
import com.io.tea.domain.repository.ApiResponse
import com.io.tea.domain.repository.MyTeaRepository
import org.koin.core.component.KoinComponent

/**
 * MyTeaListを取得するユースケース
 */
class FetchMyTeaListUseCase(
    private val myTeaRepository: MyTeaRepository
) : KoinComponent {

    suspend operator fun invoke(): UseCaseResult<List<MyTeaDTO>> {
        return when (val response = myTeaRepository.fetchMyTea()) {
            is ApiResponse.Success -> {
                val myTeaList = response.data.myTeaDtoList
                UseCaseResult.Success(myTeaList)
            }

            is ApiResponse.Failure -> {
                UseCaseResult.Failure(response.errorMessage)
            }
        }
    }
}
