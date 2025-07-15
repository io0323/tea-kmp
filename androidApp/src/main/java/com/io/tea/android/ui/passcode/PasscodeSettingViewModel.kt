package com.io.tea.android.ui.passcode

import androidx.lifecycle.ViewModel
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.resource.Colors
import com.io.tea.android.ui.passcode.model.PasscodeInputState
import com.io.tea.android.ui.passcode.model.PasscodeSettingModel
import com.io.tea.android.util.StringUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class PasscodeSettingViewModel : ViewModel(), KoinComponent {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _passcodeSettingModelModelStateFlow = MutableStateFlow(PasscodeSettingModel.default)
    val passcodeSettingModelModelStateFlow: StateFlow<PasscodeSettingModel> =
        _passcodeSettingModelModelStateFlow

    fun onInputComplete(code: String) {
        if (StringUtil.isCntNumMatched(code, 4).not()) {
            return
        }
        _passcodeSettingModelModelStateFlow.update {
            when {
                it.inputInit.isNullOrEmpty() -> {
                    it.copy(
                        inputInit = code,
                        msg = R.string.passcode_setting__text_input_confirm,
                        passcodeInputState = PasscodeInputState.INIT,
                        isInputFormClear = true,
                    )
                }

                it.inputInit != code && it.passcodeInputState == PasscodeInputState.INIT -> {
                    it.copy(
                        inputConfirm = code,
                        msg = R.string.passcode_setting__text_input_error,
                        textColor = Colors.Error,
                        passcodeInputState = PasscodeInputState.ERROR,
                        isInputFormClear = true,
                    )
                }

                it.inputInit != code && it.passcodeInputState == PasscodeInputState.ERROR -> {
                    it.copy(
                        inputConfirm = code,
                        isShowDialog = true,
                    )
                }

                else -> {
                    // NOTE : 確認入力と一致
                    it.copy(
                        inputConfirm = code,
                        passcodeInputState = PasscodeInputState.CONFIRM,
                    )

                }
            }
        }
        if (_passcodeSettingModelModelStateFlow.value.passcodeInputState == PasscodeInputState.CONFIRM) {
            onPopBack()
        }
    }

    fun onInputFormClear() {
        _passcodeSettingModelModelStateFlow.update {
            it.copy(
                isInputFormClear = false
            )
        }
    }

    fun onDismissClick() {
        _passcodeSettingModelModelStateFlow.update {
            it.copy(
                isShowDialog = false
            )
        }
        onPopBack()
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }
}