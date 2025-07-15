package com.io.tea.android.ui.delete.account.auth.state

import com.io.tea.android.ui.common.state.SmsSource


data class DeleteAccountAuthState(
    val authType: DeleteAccountAuthType = DeleteAccountAuthType.PHONE
)

enum class DeleteAccountAuthType {
    PHONE,
    EMAIL;

    companion object {
        fun fromLOptInSmsSource(source: SmsSource): DeleteAccountAuthType {
            return when (source) {
                SmsSource.PHONE -> PHONE
                SmsSource.EMAIL -> EMAIL
            }
        }
    }
}
