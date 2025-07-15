package com.io.tea.android.ui.payment.confirm.block

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.payment.confirm.model.PaymentPointConfirmModel

@Composable
internal fun PaymentPointConfirmCardBlock(paymentPointConfirmModel: PaymentPointConfirmModel) {
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
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(1.dp, TeaAppTheme.colors.Grey500),
                colors = CardDefaults.cardColors(
                    containerColor = TeaAppTheme.colors.White,
                ),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = null,
                        tint = TeaAppTheme.colors.BlueDark,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = stringResource(R.string.payment_point_confirm__description),
                        style = TeaAppTheme.typography.h5,
                        color = TeaAppTheme.colors.FontPrimary,
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(paymentPointConfirmModel.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .width(64.dp)
                        .height(42.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = paymentPointConfirmModel.regionName,
                    style = TeaAppTheme.typography.h4,
                    color = TeaAppTheme.colors.FontPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = TeaAppTheme.colors.Grey300,
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = paymentPointConfirmModel.storeName,
                style = TeaAppTheme.typography.h4,
                color = TeaAppTheme.colors.FontPrimary,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = paymentPointConfirmModel.point,
                    style = paymentPointConfirmModel.pointTextStyle,
                    color = TeaAppTheme.colors.FontPrimary,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.alignByBaseline()
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.common__gram),
                    style = TeaAppTheme.typography.h1,
                    color = TeaAppTheme.colors.FontSecondary,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    color = TeaAppTheme.colors.Blue,
                    trackColor = TeaAppTheme.colors.Grey300,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${stringResource(R.string.payment_point_confirm__remaining)} ${paymentPointConfirmModel.remainingTime}",
                    style = TeaAppTheme.typography.h3,
                    color = TeaAppTheme.colors.FontSecondary,
                )
            }
        }
    }
}
