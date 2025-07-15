package com.io.tea.android.ui.delete.region.form

import androidx.lifecycle.ViewModel
import com.io.tea.android.ui.delete.region.form.model.DeletePayItem
import com.io.tea.android.ui.delete.region.form.model.DeleteReasonItem
import com.io.tea.android.ui.delete.region.form.state.DeleteRegionFormViewModelUiState
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.notice.list.NoticeListDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

internal const val MAX_LENGTH_FEED_BACK = 255

class DeleteRegionFormViewModel : ViewModel(), KoinComponent {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _deletePayListStateFlow = MutableStateFlow<List<DeletePayItem>>(emptyList())
    val deletePayListStateFlow: MutableStateFlow<List<DeletePayItem>> = _deletePayListStateFlow

    private val _deleteReasonListStateFlow = MutableStateFlow<List<DeleteReasonItem>>(emptyList())
    val deleteReasonListStateFlow: MutableStateFlow<List<DeleteReasonItem>> =
        _deleteReasonListStateFlow

    private val _feedBackStateFlow = MutableStateFlow("")
    val feedBackStateFlow: MutableStateFlow<String> = _feedBackStateFlow

    private val _uiStateFlow = MutableStateFlow(
        DeleteRegionFormViewModelUiState(
            isErrorFeedBackLengthOver = false,
            isConfirmButtonEnabled = false
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        _deletePayListStateFlow.value = listOf(
            DeletePayItem(1, "GOGO GUAM PAY"),
            DeletePayItem(2, "おおいた旅得Lot"),
            DeletePayItem(3, "みえ旅Lot"),
        )
        _deleteReasonListStateFlow.value = listOf(
            DeleteReasonItem(1, "使用したいLotが終了したから"),
            DeleteReasonItem(2, "使用したいお店で使用できなかったから"),
            DeleteReasonItem(3, "サポート対応が悪かったから"),
            DeleteReasonItem(4, "欲しい機能がなかったから"),
            DeleteReasonItem(5, "使い方がわかりづらかったから"),
            DeleteReasonItem(6, "その他"),
        )
        initDeletePayListFirstSelect()
    }

    private fun initDeletePayListFirstSelect() {
        val deletePayList = _deletePayListStateFlow.value
        if (deletePayList.isNotEmpty()) {
            val firstItem = deletePayList.first()
            firstItem.isSelected = true
            _deletePayListStateFlow.value = deletePayList
        }
    }

    fun onSelectedDeletePay(selectedItem: DeletePayItem) {
        _deletePayListStateFlow.update { list ->
            list.map {
                if (it.id == selectedItem.id) {
                    it.copy(
                        isSelected = true
                    )
                } else {
                    it.copy(
                        isSelected = false
                    )
                }
            }
        }
        isConfirmButtonEnable()
    }

    fun onCheckedDeleteReason(checkedItem: DeleteReasonItem) {
        _deleteReasonListStateFlow.update { list ->
            list.map {
                if (it.id == checkedItem.id) {
                    it.copy(
                        isChecked = it.isChecked.not()
                    )
                } else {
                    it
                }
            }
        }
        isConfirmButtonEnable()
    }

    fun onFeedBack(value: String) {
        val isError = value.length > MAX_LENGTH_FEED_BACK
        _uiStateFlow.update {
            it.copy(
                isErrorFeedBackLengthOver = isError,
            )
        }
        _feedBackStateFlow.update { value }
    }

    private fun isConfirmButtonEnable() {
        val isSelected = _deletePayListStateFlow.value.any { it.isSelected }
        val isChecked = _deleteReasonListStateFlow.value.any { it.isChecked }
        val isEnabled = isSelected && isChecked
        _uiStateFlow.update {
            it.copy(
                isConfirmButtonEnabled = isEnabled,
            )
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onClickCancel() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onClickConfirm() {
        // Dummy
        _navigationStateFlow.value = NoticeListDestination("")
    }
}