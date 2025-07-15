package com.io.tea.android.ui.home.state

internal data class HomeUiState(
    val state: State = State.IDLE,
) {
    enum class State {
        IDLE,
        SUCCESS,
        ERROR,
        LOADING,
        REFRESH,
    }
}
