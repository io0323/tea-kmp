package com.io.tea.android.ui.login.sms

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
import com.io.tea.android.ui.account.auth.sms.block.AuthSmsBlock
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.login.model.LogInSmsCodeInputModel
import com.io.tea.android.ui.login.state.LogInSmsState

@Composable
internal fun LogInSmsCodeInputScreen(
    model: LogInSmsCodeInputModel,
    loginSmsState: LogInSmsState,
    onPopBack: () -> Unit = {},
    onClickAuth: () -> Unit = {},
    onValueChange: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.login_sms__header_title),
                onPopBack = onPopBack
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.login_sms__button_label,
                onClick = onClickAuth,
                enabled = model.isEnable
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(loginSmsState.authType.resourceId),
                style = TeaAppTheme.typography.h4,
            )
            Spacer(modifier = Modifier.height(26.dp))

            AuthSmsBlock(
                onValueChange = onValueChange
            )
        }
    }
}
