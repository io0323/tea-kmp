package com.io.tea.android.ui.account.auth.sms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.account.create.block.AccountCreateStepBlock
import com.io.tea.android.ui.account.auth.sms.block.AuthSmsBlock
import com.io.tea.android.ui.account.auth.sms.model.AuthSmsModel
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.account.create.enums.AccountCreateStep
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar

@Composable
fun AuthSmsScreen(
    model: AuthSmsModel,
    onPopBack: () -> Unit = {},
    onClickAuth: () -> Unit = {},
    onValueChange: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.sms_code_input__header_title),
                onPopBack = onPopBack,
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.sms_code_input__button_label,
                onClick = onClickAuth,
                enabled = model.isEnable
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            AccountCreateStepBlock(AccountCreateStep.STEP2)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.sms_code_input__description),
                style = TeaAppTheme.typography.h4,
            )
            Spacer(modifier = Modifier.height(26.dp))
            AuthSmsBlock(
                onValueChange = onValueChange
            )
        }
    }
}
