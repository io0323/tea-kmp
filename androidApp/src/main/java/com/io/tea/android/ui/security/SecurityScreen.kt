package com.io.tea.android.ui.security

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.menu.block.item.SettingListItemRowWithSwitch

@Composable
internal fun SecurityScreen(
    viewModel: SecurityViewModel,
    isSecurityPassCodeLockEnabled: Boolean,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.security__title),
                onPopBack = { viewModel.onPopBack() },
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                SettingListItemRowWithSwitch(
                    text = stringResource(id = R.string.security__passcode_lock),
                    isChecked = isSecurityPassCodeLockEnabled,
                    onCheckedChange = {
                        viewModel.onSecurityPassCodeLockChanged(it)
                    },
                    padding = PaddingValues(horizontal = 24.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(TeaAppTheme.colors.Grey100)
                        .padding(horizontal = 24.dp)
                        .padding(top = 17.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.security__description),
                        style = TeaAppTheme.typography.h6,
                        color = TeaAppTheme.colors.FontPrimary
                    )
                    if (isSecurityPassCodeLockEnabled) {
                        //TODO:変更 UpdateAnimationBlock
                        UpdateMessage(
                            message = stringResource(R.string.security__enable_passcode_lock),
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UpdateMessage(message: String, modifier: Modifier) {

}
