package com.io.tea.android.ui.history.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonChevron
import com.io.tea.android.ui.history.HistoryViewModel

@Composable
fun TransactionHistoryTotalPointBlock(
    totalPoint: String,
    viewModel: HistoryViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(107.dp)
            .background(color = TeaAppTheme.colors.TransactionHistoryBackground),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 23.dp)
                .background(
                    color = TeaAppTheme.colors.White,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                ),
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = stringResource(id = R.string.transaction_history__total_point),
                style = TeaAppTheme.typography.labelS,
                color = TeaAppTheme.colors.FontPrimary,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = totalPoint,
                    style = TeaAppTheme.typography.title2,
                    color = TeaAppTheme.colors.FontPrimary,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.common__gram),
                    style = TeaAppTheme.typography.labelL,
                    color = TeaAppTheme.colors.FontPrimary,
                    maxLines = 1,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }
            Spacer(modifier = Modifier.height(11.dp))
        }
        ButtonChevron(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 8.dp, y = 8.dp),
            onClick = { viewModel.onPopBack() }
        )
    }
}
