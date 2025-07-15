package com.io.tea.android.ui.payment.confirm.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class PaymentPointConfirmModel(
    val backgroundColor: Color,
    val regionName: String,
    val imageUrl: String,
    val storeName: String,
    val point: String,
    val pointDigits: PointDigits,
    val pointTextStyle: TextStyle,
    val remainingTime: String,
) {
    companion object {
        val default: PaymentPointConfirmModel = PaymentPointConfirmModel(
            backgroundColor = Color.White,
            regionName = "",
            imageUrl = "",
            storeName = "",
            point = "",
            pointDigits = PointDigits.WITHIN_DIGITS_5,
            pointTextStyle = TextStyle.Default,
            remainingTime = "",
        )
    }
}

enum class PointDigits {
    WITHIN_DIGITS_5,
    DIGITS_6,
    DIGITS_7;

    companion object {
        fun fromValue(value: Long): PointDigits {
            val length = "$value".length
            return when (length) {
                in 1..5 -> WITHIN_DIGITS_5
                6 -> DIGITS_6
                else -> DIGITS_7
            }
        }
    }
}
