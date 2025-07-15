package com.io.tea.android.ui.payment.complete.model

import androidx.compose.ui.graphics.Color

data class PaymentCompleteModel(
    val backgroundColor: Color,
    val regionName: String,
    val imageUrl: String,
    val storeName: String,
    val point: String,
    val acceptedDate: String,
    val paymentNumber: String,
) {
    companion object {
        val default: PaymentCompleteModel = PaymentCompleteModel(
            backgroundColor = Color.White,
            regionName = "",
            imageUrl = "",
            storeName = "",
            point = "",
            acceptedDate = "",
            paymentNumber = "",
        )
    }
}
