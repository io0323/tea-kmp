package com.io.tea.android.ui.login.state

import androidx.annotation.StringRes
import com.io.tea.android.R
import com.io.tea.android.ui.common.state.SmsSource

data class LogInSmsState(
    val authType: LogInSmsAuthType
)

enum class LogInSmsAuthType(@StringRes val resourceId: Int) {
    PHONE(resourceId = R.string.login_sms__phone_description),
    EMAIL(resourceId = R.string.login_sms__email_description);

    companion object {
        fun fromLogInSmsSource(source: SmsSource): LogInSmsAuthType {
            return when (source) {
                SmsSource.PHONE -> PHONE
                SmsSource.EMAIL -> EMAIL
            }
        }
    }
}
