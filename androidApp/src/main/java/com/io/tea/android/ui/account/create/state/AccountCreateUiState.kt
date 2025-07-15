package com.io.tea.android.ui.account.create.state

internal data class AccountCreateUiState(
    val state: State = State.IDLE,
) {
    enum class State {
        IDLE,
        SUCCESS,
        ERROR
    }
}
