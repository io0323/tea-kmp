package com.io.tea.android.ui.delete.account.form

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.delete.account.form.model.DeleteAccountItem
import com.io.tea.android.ui.delete.account.form.state.DeleteAccountFormViewModelUiState
import com.io.tea.android.ui.notice.list.NoticeListDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

internal const val MAX_LENGTH_FEED_BACK = 255

class DeleteAccountFormViewModel : ViewModel(), KoinComponent {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _deleteReasonListStateFlow = MutableStateFlow<List<DeleteAccountItem>>(emptyList())
    val deleteReasonListStateFlow: MutableStateFlow<List<DeleteAccountItem>> =
        _deleteReasonListStateFlow

    private val _feedBackStateFlow = MutableStateFlow("")
    val feedBackStateFlow: MutableStateFlow<String> = _feedBackStateFlow

    private val _uiStateFlow = MutableStateFlow(
        DeleteAccountFormViewModelUiState(
            isErrorFeedBackLengthOver = false,
            isConfirmButtonEnabled = false
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        _deleteReasonListStateFlow.value = listOf(
            DeleteAccountItem(1, "使用したいLotが終了したから"),
            DeleteAccountItem(2, "使用したいお店で使用できなかったから"),
            DeleteAccountItem(3, "サポート対応が悪かったから"),
            DeleteAccountItem(4, "欲しい機能がなかったから"),
            DeleteAccountItem(5, "使い方がわかりづらかったから"),
            DeleteAccountItem(6, "その他"),
        )
    }

    fun onCheckedDeleteReason(checkedItem: DeleteAccountItem) {
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
        val isEnabled = _deleteReasonListStateFlow.value.any { it.isChecked }
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