package com.io.tea.android.ui.home.state

internal data class HomeDisplayState(
    val displayType: DisplayState = DisplayState.INIT,
) {
    enum class DisplayState {
        INIT,
        SUCCESS,
        empty_ONE,
        empty_TWO,
    }
}
