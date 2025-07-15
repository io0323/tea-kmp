package com.io.tea.android.ui.passcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.passcode.model.PasscodeSettingModel
import com.io.tea.android.ui.account.auth.sms.block.AuthSmsBlock
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.common.component.TextButton

@Composable
fun PasscodeSettingScreen(
    viewModel: PasscodeSettingViewModel,
    model: PasscodeSettingModel,
    onInputComplete: (String) -> Unit,
    onInputFormClear: () -> Unit,
    onDismissClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.screen__passcode_setting),
                onPopBack = { viewModel.onPopBack() },
            )
        },
        bottomBar = {
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(model.msg),
                style = TeaAppTheme.typography.h4,
                color = model.textColor
            )
            Spacer(modifier = Modifier.height(51.dp))
            AuthSmsBlock(
                smsAuthCodeLength = 4,
                isPasswordMode = true,
                isInputFormClear = model.isInputFormClear,
                onInputComplete = onInputComplete,
                onInputFormClear = onInputFormClear,
            )
        }
    }
    if (model.isShowDialog) {
        ShowAlertDialog(
            onDismissClick = { onDismissClick() },
        )
    }
}

@Composable
internal fun ShowAlertDialog(
    onDismissClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {},
        title = {},
        text = {
            Text(
                text = stringResource(R.string.passcode_setting__text_dialog_error)
            )
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                textResId = R.string.common__close,
                isEnabled = true,
                onClick = { onDismissClick() }
            )
        }
    )
}
