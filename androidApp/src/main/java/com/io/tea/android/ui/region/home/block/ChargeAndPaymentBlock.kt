package com.io.tea.android.ui.region.home.block

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.region.home.model.ChargeAndPaymentButtonState
import com.io.tea.android.ui.region.home.model.ChargeAndPaymentModel

@Composable
internal fun ChargeAndPaymentBlock(
    chargeAndPaymentModel: ChargeAndPaymentModel,
    onChargeClick: () -> Unit,
    onPaymentClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()) {
        ChargeAndPaymentButton(
            buttonState = chargeAndPaymentModel.chargeButtonState,
            regionColor = chargeAndPaymentModel.regionColor,
            iconId = android.R.drawable.ic_menu_close_clear_cancel,
            textId = R.string.region_home__charge,
            onClick = onChargeClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(15.dp))

        ChargeAndPaymentButton(
            buttonState = chargeAndPaymentModel.paymentButtonState,
            regionColor = chargeAndPaymentModel.regionColor,
            iconId = android.R.drawable.ic_menu_close_clear_cancel,
            textId = R.string.region_home__payment,
            onClick = onPaymentClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ChargeAndPaymentButton(
    buttonState: ChargeAndPaymentButtonState,
    regionColor: Color,
    @DrawableRes iconId: Int,
    @StringRes textId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // 両方のボタンが、ChargeAndPaymentButtonState.HIDDENになることはない想定
    val updateModifier = when (buttonState) {
        ChargeAndPaymentButtonState.ENABLE -> {
            modifier.clickable(onClick = onClick)
        }
        ChargeAndPaymentButtonState.DISABLE -> {
            modifier.alpha(0.2f)
        }
        ChargeAndPaymentButtonState.HIDDEN -> {
            modifier.alpha(0f)
        }
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = TeaAppTheme.colors.White),
        shape = RoundedCornerShape(20.dp),
        modifier = updateModifier.height(72.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = iconId),
                tint = regionColor,
                contentDescription = "icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(id = textId),
                style = TeaAppTheme.typography.label,
                color = TeaAppTheme.colors.FontSecondary
            )
        }
    }
}
