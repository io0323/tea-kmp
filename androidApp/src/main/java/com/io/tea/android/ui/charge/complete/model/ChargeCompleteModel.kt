package com.io.tea.android.ui.charge.complete.model

import androidx.compose.ui.graphics.Color

data class ChargeCompleteModel(
    val backgroundColor: Color,
    val plusPoint: String,
    val totalPoint: String,
    val period: String,
) {
    companion object {
        val default: ChargeCompleteModel = ChargeCompleteModel(
            backgroundColor = Color.White,
            plusPoint = "",
            totalPoint = "",
            period = "",
        )
    }
}
