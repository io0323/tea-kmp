package com.io.tea.android.ui.history.block

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TeaAppHorizontalDivider
import com.io.tea.android.ui.history.model.TransactionHistoryModel
import com.io.tea.android.ui.history.model.TransactionHistoryType

@Composable
fun TransactionHistoryListItem(
    model: TransactionHistoryModel
) {
    TeaAppHorizontalDivider()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Icon(
            painter = painterResource(id = model.upDown),
            contentDescription = "contentdescription",
            tint = model.upDownTint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.weight(1f)
        ) {
            when (model.transactionType) {
                TransactionHistoryType.CHARGE -> {
                    Text(
                        text = model.useDate,
                        style = TeaAppTheme.typography.caption,
                        color = Colors.Font.second,
                        maxLines = 1,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                TransactionHistoryType.PAYMENT, TransactionHistoryType.REPAYMENT -> {
                    Text(
                        text = model.storeName,
                        style = TeaAppTheme.typography.caption,
                        color = Colors.Font.second,
                        maxLines = 2,
                        softWrap = true,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Text(
                text = model.transactionNumber,
                style = TeaAppTheme.typography.caption,
                color = Colors.Font.second,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = model.transactionDate,
                style = TeaAppTheme.typography.caption,
                color = model.transactionDateColor,
                maxLines = 1,
                softWrap = true,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.weight(0.5f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = model.point,
                    style = TeaAppTheme.typography.poppins,
                    color = Colors.Font.second,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.common__gram),
                    style = TeaAppTheme.typography.body1,
                    color = Colors.Font.primary,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = model.pointGray,
                    style = TeaAppTheme.typography.subtitle3,
                    color = TeaAppTheme.colors.Grey500,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.common__gram),
                    style = TeaAppTheme.typography.caption,
                    color = TeaAppTheme.colors.Grey500,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
