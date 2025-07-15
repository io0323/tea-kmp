package com.io.tea.android.ui.common.state

/**
 * ボタンの状態
 */
sealed class ButtonState {
    data object Active : ButtonState()
    data object Inactive : ButtonState()

    fun toggle(): ButtonState = when (this) {
        is Active -> Inactive
        is Inactive -> Active
    }
}
