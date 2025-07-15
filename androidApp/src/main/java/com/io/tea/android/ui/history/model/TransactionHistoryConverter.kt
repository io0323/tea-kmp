package com.io.tea.android.ui.history.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.io.tea.android.resource.Colors
import com.io.tea.android.ui.history.state.TransactionHistory
import com.io.tea.android.util.DateUtil
import com.io.tea.android.util.StringUtil

object TransactionHistoryConverter {

    @RequiresApi(Build.VERSION_CODES.O)
    fun convert(model: TransactionHistory): TransactionHistoryModel {
        val transactionNumber = when (model.transactionType) {
            TransactionHistoryType.CHARGE -> "Lot番号："
            TransactionHistoryType.PAYMENT -> "決済番号："
            TransactionHistoryType.REPAYMENT -> "返金番号：["
        }
        val transactionDate = when (model.transactionType) {
            TransactionHistoryType.CHARGE -> "有効期限："
            else -> "受付日時："
        }
        val transactionPoint = when (model.transactionType) {
            TransactionHistoryType.PAYMENT -> "- "
            else -> "+ "
        }
        return TransactionHistoryModel(
            regionId = model.regionId,
            storeName = model.storeName,
            useDate = "利用日 : " + DateUtil.toFormatYMD(
                date = model.useDate,
                pattern = "yyyy/MM/dd"
            ),
            transactionNumber = transactionNumber + model.transactionNumber,
            transactionDate = transactionDate + DateUtil.toFormatYMD(
                date = model.transactionDate,
                pattern = "yyyy/MM/dd"
            ),
            transactionDateColor = if (model.transactionType == TransactionHistoryType.PAYMENT) {
                Colors.BlueDark
            } else {
                Colors.LabelBackground
            },
            point = transactionPoint + StringUtil.formatComma(model.point.toLong()),
            pointGray = StringUtil.formatComma(model.pointGray.toLong()),
            upDown = if (model.transactionType == TransactionHistoryType.PAYMENT) {
                android.R.drawable.arrow_up_float
            } else {
                android.R.drawable.arrow_down_float
            },
            upDownTint = if (model.transactionType == TransactionHistoryType.PAYMENT) {
                Colors.LabelBackground
            } else {
                Colors.BlueDark
            },
            transactionType = model.transactionType
        )
    }
}
