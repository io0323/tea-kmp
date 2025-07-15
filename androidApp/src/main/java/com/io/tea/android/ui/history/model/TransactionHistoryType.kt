package com.io.tea.android.ui.history.model

enum class TransactionHistoryType(val value: String) {
    // Lot時
    CHARGE("charge"),

    // 支払時
    PAYMENT("payment"),

    // 返金時
    REPAYMENT("repayment"),
}