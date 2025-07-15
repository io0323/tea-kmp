package com.io.tea.android.ui.notice.common

import androidx.compose.ui.graphics.Color

data class NoticeModel(
    val regionColor: Color,
    val payName: String,
    val msg: String,
    val url: String,
    val date: String,
)
