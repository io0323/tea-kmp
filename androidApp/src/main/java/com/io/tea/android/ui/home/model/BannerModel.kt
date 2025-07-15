package com.io.tea.android.ui.home.model

import androidx.compose.ui.graphics.Color

data class BannerModel(
    val imageURL: String,
    val imageDescription: String,
    val bannerName: String,
    val msg: String,
    val point: String,
    val statusColor: Color,
) {
    companion object {
        val default = BannerModel(
            imageURL = "",
            imageDescription = "",
            bannerName = "",
            msg = "",
            point = "",
            statusColor = Color.Red,
        )
    }
}

