package com.io.tea.android.ui.account.auth.sms.model

data class AuthSmsModel(
    val authCode: String?,
    val isEnable: Boolean,
) {
    companion object {
        val default = AuthSmsModel(
            authCode = null,
            isEnable = false,
        )
    }
}
