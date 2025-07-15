package com.io.tea.android.ui.delete.account.confirm

import androidx.lifecycle.ViewModel
import com.io.tea.android.ui.delete.account.confirm.state.DeleteAccountConfirmViewModelUiState
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.notice.list.NoticeListDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class DeleteAccountConfirmViewModel : ViewModel(), KoinComponent {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _deleteAccountListStateFlow = MutableStateFlow<List<String>>(emptyList())
    val deleteAccountListStateFlow: MutableStateFlow<List<String>> = _deleteAccountListStateFlow

    private val _feedBackStateFlow = MutableStateFlow<String?>(null)
    val feedBackStateFlow: MutableStateFlow<String?> = _feedBackStateFlow

    private val _cautionListStateFlow = MutableStateFlow<List<Int>>(emptyList())
    val cautionListStateFlow: MutableStateFlow<List<Int>> = _cautionListStateFlow

    private val _isCheckedStateFlow = MutableStateFlow(false)
    val isCheckedStateFlow: MutableStateFlow<Boolean> = _isCheckedStateFlow

    private val _uiStateFlow = MutableStateFlow(
        DeleteAccountConfirmViewModelUiState(
            isConfirmButtonEnabled = false
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()


    init {
        _deleteAccountListStateFlow.value = listOf(
            "使用したいLotが終了したから",
            "使用したいお店で使用できなかったから",
            "その他",
        )
        _feedBackStateFlow.value = "また気になるLotがあったら使用します！"
        _cautionListStateFlow.value = listOf(
            R.string.delete_account_confirm__caution_text_one,
            R.string.delete_account_confirm__caution_text_two,
            R.string.delete_account_confirm__caution_text_three,
            R.string.delete_account_confirm__caution_text_four,
            R.string.delete_account_confirm__caution_text_five,
        )
    }

    private fun isConfirmButtonEnable() {
        val isEnabled = _isCheckedStateFlow.value
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

    fun onCheckedDeleteAgree(isChecked: Boolean) {
        _isCheckedStateFlow.value = isChecked.not()
        isConfirmButtonEnable()
    }

    fun onClickCancel() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onClickConfirm() {
        if (_uiStateFlow.value.isConfirmButtonEnabled) {
            // Dummy
            _navigationStateFlow.value = NoticeListDestination("")
        }
    }
}