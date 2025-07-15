package com.io.tea.android.ui.passcode.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.io.tea.android.R
import com.io.tea.android.resource.Colors

data class PasscodeSettingModel(
    @StringRes
    val msg: Int,
    @ColorRes
    val textColor: Color,
    val passcodeInputState: PasscodeInputState,
    val inputInit: String?,
    val inputConfirm: String?,
    val isInputFormClear: Boolean,
    val isShowDialog: Boolean,
) {
    companion object {
        val default: PasscodeSettingModel = PasscodeSettingModel(
            msg = R.string.passcode_setting__text_init,
            textColor = Colors.FontPrimary,
            passcodeInputState = PasscodeInputState.INIT,
            inputInit = null,
            inputConfirm = null,
            isInputFormClear = false,
            isShowDialog = false,
        )
    }
}
