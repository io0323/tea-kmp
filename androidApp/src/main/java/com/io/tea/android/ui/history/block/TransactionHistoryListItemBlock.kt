package com.io.tea.android.ui.history.block

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.io.tea.android.ui.history.model.TransactionHistoryWithHeaderModel

@Composable
fun TransactionHistoryListBlock(
    modelList: List<TransactionHistoryWithHeaderModel>
) {
    modelList.forEach { (headerYearMonth, transactionHistoryList) ->
        Column {
            TransactionHistoryItemHeaderBlock(headerYearMonth = headerYearMonth)
            transactionHistoryList.map {
                TransactionHistoryListItem(it)
            }
        }
    }
}
