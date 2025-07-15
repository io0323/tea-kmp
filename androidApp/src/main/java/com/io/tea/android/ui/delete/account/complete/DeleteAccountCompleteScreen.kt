package com.io.tea.android.ui.delete.account.complete

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar

@Composable
internal fun DeleteAccountCompleteScreen(
    viewModel: DeleteAccountCompleteViewModel,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.delete_account_complete__header_title)
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.common__close,
                enabled = true,
                onClick = { viewModel.onClickCloseButton() }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.delete_account_complete__guidance_title),
                    style = TeaAppTheme.typography.h2,
                    color = TeaAppTheme.colors.FontPrimary,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.delete_account_complete__guidance_text),
                    style = TeaAppTheme.typography.h5,
                    color = TeaAppTheme.colors.FontPrimary,
                )
            }
        }
    }
}