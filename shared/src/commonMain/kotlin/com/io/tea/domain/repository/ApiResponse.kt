package com.io.tea.domain.repository

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Failure(val errorMessage: String) : ApiResponse<Nothing>()
}

suspend fun <T> handleApi(execute: suspend () -> T): ApiResponse<T> {
    return try {
        val response = execute()
        ApiResponse.Success(data = response)
    } catch (Exception: Exception) {
        ApiResponse.Failure(errorMessage = Exception.message ?: "")
    }
}
