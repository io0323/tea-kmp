package com.io.tea.android.ui.login.block

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.login.state.LogInForm

@Composable
internal fun descriptionBlock(
    loginForm: LogInForm,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(loginForm.resourceId),
        style = TeaAppTheme.typography.h4,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(loginForm.descriptionSpace))
}
