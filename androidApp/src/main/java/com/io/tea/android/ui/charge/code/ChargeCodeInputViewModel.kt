package com.io.tea.android.ui.charge.code

import androidx.lifecycle.ViewModel
import com.io.tea.android.ui.charge.code.model.ChargeCodeInputModel
import com.io.tea.android.ui.charge.confirm.ChargeCodeConfirmDestination
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.util.ColorUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal const val INPUT_FORMS = 4
internal const val INPUT_DIGITS = 5

class ChargeCodeInputViewModel : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _codeInputModelStateFlow = MutableStateFlow(ChargeCodeInputModel.default)
    val codeInputModelStateFlow: StateFlow<ChargeCodeInputModel> =
        _codeInputModelStateFlow

    init {
        // TODO: サンプルデータ
        val backgroundColorCode = "#EA613B"
        _codeInputModelStateFlow.update {
            it.copy(
                backgroundColor = ColorUtil.hexToColor(backgroundColorCode),
                inputCode = emptyList()
            )
        }
    }

    fun onValueChange(value: List<String>) {
        val isEnable = value.all { it.length == INPUT_DIGITS }
        _codeInputModelStateFlow.update {
            it.copy(
                inputCode = value,
                isEnable = isEnable,
            )
        }
    }

    fun onClickButtonNext() {
        _navigationStateFlow.value = ChargeCodeConfirmDestination("")
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
