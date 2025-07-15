package com.io.tea.android.ui.payment.point

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.common.component.ButtonPrimary
import com.io.tea.android.ui.common.component.model.PaymentPointInputModel
import com.io.tea.android.ui.payment.block.InputPointFieldBlock

@Composable
internal fun PaymentPointInputScreen(
    pointInputModel: PaymentPointInputModel,
    onCloseClick: () -> Unit,
    onValueChange: (String) -> Unit,
    onClickNext: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.payment_point_input__header_title),
                actions = {
                    IconButton(onClick = onCloseClick) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = "close",
                            tint = TeaAppTheme.colors.BlueDark,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },

        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(pointInputModel.backgroundColor)
        ) {
            Card(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = TeaAppTheme.colors.White),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.37.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = pointInputModel.regionName,
                        style = TeaAppTheme.typography.h4,
                        color = TeaAppTheme.colors.FontPrimary,
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    InputPointFieldBlock(
                        inputPoint = pointInputModel.inputPoint,
                        availablePoint = pointInputModel.availablePoint,
                        onValueChange = onValueChange,
                        interactionSource = interactionSource
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    ButtonPrimary(
                        isEnabled = pointInputModel.isEnable,
                        onClick = onClickNext,
                        textResId = R.string.payment_point_input__button_label,
                        buttonModifier = Modifier
                            .padding(horizontal = 40.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}
