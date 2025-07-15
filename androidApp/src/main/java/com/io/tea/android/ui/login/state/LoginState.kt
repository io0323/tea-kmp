package com.io.tea.android.ui.login.state

import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.R

data class LogInState(
    val form: LogInForm = LogInForm.PHONE
)

enum class LogInForm(@StringRes val resourceId: Int, val descriptionSpace: Dp) {
    PHONE(
        resourceId = R.string.login__phone_description,
        descriptionSpace = 16.dp
    ),
    EMAIL(
        resourceId = R.string.login__email_description,
        descriptionSpace = 41.dp // Figma上は35dpだが、電話番号とメールの切替時にボタンの位置がガタつくので補正
    )
}
