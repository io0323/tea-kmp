package com.io.tea.android.ui.account.auth.sms.state

internal data class AuthSmsUiState(
    val state: State = State.IDLE,
) {
    enum class State {
        IDLE,
        SUCCESS,
        ERROR
    }
}
