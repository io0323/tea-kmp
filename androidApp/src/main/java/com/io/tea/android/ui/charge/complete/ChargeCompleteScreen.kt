package com.io.tea.android.ui.charge.complete

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
import com.io.tea.android.ui.charge.complete.block.ChargeCompleteCardBlock
import com.io.tea.android.ui.charge.complete.model.ChargeCompleteModel
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TeaAppBottomButtonType
import com.io.tea.android.ui.common.TopBar

@Composable
internal fun ChargeCompleteScreen(
    chargeCompleteModel: ChargeCompleteModel,
    onPopBack: () -> Unit,
    onRegionTopClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.charge_complete__header_title),
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
                resourceId = R.string.charge_complete__bottom_button,
                enabled = true,
                onClick = onRegionTopClick,
                buttonType = TeaAppBottomButtonType.SECONDARY,
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(chargeCompleteModel.backgroundColor)
        ) {
            ChargeCompleteCardBlock(chargeCompleteModel = chargeCompleteModel)
        }
    }
}
