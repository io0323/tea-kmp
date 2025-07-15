package com.io.tea.android.ui.history.state

import com.io.tea.android.ui.history.model.TransactionHistoryType
import com.io.tea.android.ui.history.model.TransactionHistoryWithHeaderModel

sealed class TransactionHistoryUseCaseState {
    data object Initial : TransactionHistoryUseCaseState()
    data object Loading : TransactionHistoryUseCaseState()
    data class Success(val data: List<TransactionHistoryWithHeaderModel>) : TransactionHistoryUseCaseState()
    data class Failure(val errorMessage: String) : TransactionHistoryUseCaseState()
}

data class TransactionHistory(
    val regionId: Long,
    // NOTE : (想定)店舗名
    val storeName: String,
    // NOTE : (想定)利用日
    val useDate: String,
    // NOTE : (想定)取引番号 [Lot番号 / 決済番号 / 返金番号]
    val transactionNumber: String,
    // NOTE : (想定)有効期限
    val transactionDate: String,
    // NOTE : (想定)Lot g
    val point: String,
    // NOTE : (想定)Lot g
    val pointGray: String,
    // NOTE : (想定)UpDownフラグ
    val upDown: String,
    // 取引タイプ
    val transactionType: TransactionHistoryType,
)
