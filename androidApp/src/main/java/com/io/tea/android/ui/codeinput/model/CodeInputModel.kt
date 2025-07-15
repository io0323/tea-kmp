package com.io.tea.android.ui.codeinput.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.io.tea.android.resource.Colors

data class CodeInputModel(
    val inputCode: String,
    @StringRes
    val errorText: Int?,
    @ColorRes
    val drawLineColor: Color,
    val isEnable: Boolean,
) {
    companion object {
        val default: CodeInputModel = CodeInputModel(
            inputCode = "",
            errorText = null,
            drawLineColor = Colors.Base.primary,
            isEnable = false,
        )
    }
}
