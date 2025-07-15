package com.io.tea.android.ui.payment.complete.block

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.payment.complete.model.PaymentCompleteModel

@Composable
internal fun PaymentCompleteCardBlock(paymentCompleteModel: PaymentCompleteModel) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(paymentCompleteModel.imageUrl)
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
                    text = paymentCompleteModel.regionName,
                    style = TeaAppTheme.typography.h4,
                    color = TeaAppTheme.colors.FontPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            HorizontalDivider(
                thickness = 1.dp,
                color = TeaAppTheme.colors.Grey300,
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = paymentCompleteModel.storeName,
                style = TeaAppTheme.typography.h4,
                color = TeaAppTheme.colors.FontPrimary,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = paymentCompleteModel.point,
                    style = TeaAppTheme.typography.title2,
                    color = TeaAppTheme.colors.FontPrimary,
                    textAlign = TextAlign.Right,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.common__gram),
                    style = TeaAppTheme.typography.h1,
                    color = TeaAppTheme.colors.FontSecondary,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${paymentCompleteModel.acceptedDate} ${stringResource(R.string.payment_complete__accept)}",
                style = TeaAppTheme.typography.caption,
                color = TeaAppTheme.colors.FontPrimary,
            )
            Spacer(modifier = Modifier.height(8.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.payment_complete__payment_number),
                    style = TeaAppTheme.typography.caption,
                    color = TeaAppTheme.colors.FontSecondary,
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = paymentCompleteModel.paymentNumber,
                    style = TeaAppTheme.typography.caption,
                    color = TeaAppTheme.colors.FontPrimary,
                )
            }
        }
    }
}
