package com.io.tea.android.ui.common.component.model

import androidx.compose.ui.graphics.Color

data class PaymentPointInputModel(
    val backgroundColor: Color,
    val regionName: String,
    val inputPoint: String,
    val availablePoint: String,
    val isEnable: Boolean,
) {
    companion object {
        val default: PaymentPointInputModel = PaymentPointInputModel(
            backgroundColor = Color.White,
            regionName = "",
            inputPoint = "",
            availablePoint = "",
            isEnable = false,
        )
    }
}
