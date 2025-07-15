package com.io.tea.android.ui.charge.qr.block

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun ManuallyChargeCodeBlock(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = 12.dp, bottom = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(TeaAppTheme.colors.Black50)
    ) {
        Text(
            text = stringResource(R.string.charge_qr_scan__manually_title),
            style = TeaAppTheme.typography.h5,
            color = TeaAppTheme.colors.FontContrast,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            shape = RoundedCornerShape(60),
            border = BorderStroke(
                width = 1.dp,
                color = TeaAppTheme.colors.Grey500
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = TeaAppTheme.colors.White
            ),
            modifier = modifier.height(34.dp), // Figma通り(30dp)では文字が途切れる
            onClick = onClick,
        ) {
            Text(
                text = stringResource(R.string.charge_qr_scan__manually_button),
                style = TeaAppTheme.typography.btn,
                color = TeaAppTheme.colors.FontPrimary,
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
    }
}
