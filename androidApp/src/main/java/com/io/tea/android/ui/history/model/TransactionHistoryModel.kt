package com.io.tea.android.ui.history.model

import androidx.compose.ui.graphics.Color

data class TransactionHistoryWithHeaderModel(
    val headerYearMonth: String,
    val transactionHistoryList: List<TransactionHistoryModel>
)

data class TransactionHistoryModel(
    val regionId: Long,
    // NOTE : (想定)店舗名
    val storeName: String,
    // NOTE : (想定)利用日
    val useDate: String,
    // NOTE : (想定)Lot番号
    val transactionNumber: String,
    // NOTE : (想定)有効期限/受付日時
    val transactionDate: String,
    // NOTE : (想定)有効期限/受付日時文字色
    val transactionDateColor: Color,
    // NOTE : (想定)Lot g
    val point: String,
    // NOTE : (想定)Lot g
    val pointGray: String,
    // NOTE : (想定)UpDownフラグ
    val upDown: Int,
    // NOTE : (想定)UpDown色
    val upDownTint: Color,
    // 取引タイプ
    val transactionType: TransactionHistoryType
)