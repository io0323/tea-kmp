package com.io.tea.android.ui.login.model

data class LogInSmsCodeInputModel(
    val authCode: String?,
    val isEnable: Boolean,
) {
    companion object {
        val default = LogInSmsCodeInputModel(
            authCode = null,
            isEnable = false,
        )
    }
}
