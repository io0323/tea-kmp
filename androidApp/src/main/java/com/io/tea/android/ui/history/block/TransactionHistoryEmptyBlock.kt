package com.io.tea.android.ui.history.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun TransactionHistoryemptyBlock() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "TransactionHistoryempty",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .width(102.dp)
                .height(87.dp)
        )
        Text(
            text = stringResource(R.string.transaction_history__empty),
            textAlign = TextAlign.Center,
            style = TeaAppTheme.typography.h4,
            color = TeaAppTheme.colors.FontSecondary
        )
    }
}
