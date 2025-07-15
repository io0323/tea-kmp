package com.io.tea.android.ui.payment.complete

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TeaAppBottomButtonType
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.payment.complete.block.PaymentCompleteCardBlock
import com.io.tea.android.ui.payment.complete.model.PaymentCompleteModel

@Composable
internal fun PaymentCompleteScreen(
    paymentCompleteModel: PaymentCompleteModel,
    onPopBack: () -> Unit,
    onRegionTopClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.payment_complete__header_title),
                actions = {
                    IconButton(onClick = onPopBack) {
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
        bottomBar = {
            BottomBar(
                resourceId = R.string.payment_complete__bottom_button,
                enabled = true,
                onClick = onRegionTopClick,
                buttonType = TeaAppBottomButtonType.SECONDARY,
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(paymentCompleteModel.backgroundColor)
        ) {
            PaymentCompleteCardBlock(paymentCompleteModel = paymentCompleteModel)
        }
    }
}
