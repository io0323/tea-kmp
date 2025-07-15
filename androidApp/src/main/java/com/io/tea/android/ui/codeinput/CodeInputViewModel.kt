package com.io.tea.android.ui.codeinput

import androidx.lifecycle.ViewModel
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.resource.Colors
import com.io.tea.android.ui.codeinput.model.CodeInputModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CodeInputViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _codeInputModelStateFlow = MutableStateFlow(CodeInputModel.default)
    val codeInputModelStateFlow: StateFlow<CodeInputModel> =
        _codeInputModelStateFlow

    fun onValueChange(value: String) {
        val errorText = when (value.length) {
            in 4..8 -> null
            else -> R.string.code_input__error
        }
        val drawLineColor = if (errorText == null) {
            Colors.Base.primary
        } else {
            Colors.Error
        }

        _codeInputModelStateFlow.update {
            it.copy(
                inputCode = value,
                errorText = errorText,
                drawLineColor = drawLineColor,
                isEnable = errorText == null,
            )
        }
    }

    fun onClickButtonAdd() {
        // TODO : Mock
        _codeInputModelStateFlow.update {
            it.copy(
                errorText = R.string.code_input__add_error,
                drawLineColor = Colors.Error,
            )
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}