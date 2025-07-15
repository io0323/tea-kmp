package com.io.tea.android.ui.charge.code.model

import androidx.compose.ui.graphics.Color

data class ChargeCodeInputModel(
    val backgroundColor: Color,
    val inputCode: List<String>,
    val isEnable: Boolean,
) {
    companion object {
        val default: ChargeCodeInputModel = ChargeCodeInputModel(
            backgroundColor = Color.White,
            inputCode = emptyList(),
            isEnable = false,
        )
    }
}
