package com.io.tea.android.ui.charge.confirm.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.charge.confirm.model.ChargeCodeConfirmModel
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun ChargeCodeConfirmCardBlock(chargeCodeConfirmModel: ChargeCodeConfirmModel) {
    Card(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = TeaAppTheme.colors.White),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.37.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.charge_code_confirm__description),
                style = TeaAppTheme.typography.h4,
                color = TeaAppTheme.colors.FontPrimary,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(32.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = TeaAppTheme.colors.Grey50
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = "point",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = stringResource(R.string.charge_code_confirm__point),
                            style = TeaAppTheme.typography.h4,
                            color = TeaAppTheme.colors.BlueDark,
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = chargeCodeConfirmModel.chargePoint,
                            style = TeaAppTheme.typography.title2,
                            color = TeaAppTheme.colors.FontPrimary,
                        )
                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = stringResource(R.string.common__gram),
                            style = TeaAppTheme.typography.h1,
                            color = TeaAppTheme.colors.FontSecondary,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = chargeCodeConfirmModel.period,
                style = TeaAppTheme.typography.h6,
                color = TeaAppTheme.colors.FontSecondary,
                textAlign = TextAlign.Center,
            )
        }
    }
}
