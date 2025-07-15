package com.io.tea.android.ui.payment.block

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun QrScandescriptionBlock() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.payment_qr_scan__header_title),
            style = TeaAppTheme.typography.h3,
            color = TeaAppTheme.colors.FontContrast,
        )
        Spacer(modifier = Modifier.height(103.dp))
        Text(
            text = stringResource(R.string.payment_qr_scan__description),
            style = TeaAppTheme.typography.h6,
            color = TeaAppTheme.colors.FontContrast,
        )
    }
}
