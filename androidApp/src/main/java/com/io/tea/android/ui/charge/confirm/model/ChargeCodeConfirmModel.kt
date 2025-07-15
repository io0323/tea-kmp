package com.io.tea.android.ui.charge.confirm.model

import androidx.compose.ui.graphics.Color

data class ChargeCodeConfirmModel(
    val backgroundColor: Color,
    val chargePoint: String,
    val period: String,
) {
    companion object {
        val default: ChargeCodeConfirmModel = ChargeCodeConfirmModel(
            backgroundColor = Color.White,
            chargePoint = "",
            period = "",
        )
    }
}
