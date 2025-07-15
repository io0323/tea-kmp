package com.io.tea.android.ui.home.model

import androidx.compose.ui.graphics.Color

data class TeaModel(
    val imageURL: String,
    val imageDescription: String,
    val teaName: String,
    val msg: String,
    val detail: String,
    val statusColor: Color,
) {
    companion object {
        val default = TeaModel(
            imageURL = "",
            imageDescription = "",
            teaName = "",
            msg = "",
            detail = "",
            statusColor = Color.Red,
        )
    }
}
