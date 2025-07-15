package com.io.tea.android.ui.region.list.state

import com.io.tea.android.ui.common.state.ButtonState


sealed class RegionListLoadingState {
    data object Initial : RegionListLoadingState()
    data object Loading : RegionListLoadingState()
    data object Success : RegionListLoadingState()
    data class Failure(val errorMessage: String) : RegionListLoadingState()
}

/**
 * Lot一覧画面のコンポーネントの状態
 */
data class RegionListComponent(
    /** 未追加ボタン */
    val noAddedButton: ButtonState = ButtonState.Inactive,
)

data class RegionFilter(
    val id: Int,
    val text: String,
    var isChecked: Boolean = false,
)