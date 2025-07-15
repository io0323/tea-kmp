package com.io.tea.android.ui.common.block

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.ui.common.PasswordField

@SuppressLint("ModifierParameter")
@Composable
internal fun PasswordBlock(
    interactionSource: MutableInteractionSource,
    password: String = "",
    passwordForgotLink: String = "",
    isError: Boolean = false,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    PasswordField(
        interactionSource = interactionSource,
        password = password,
        isError = isError,
        onValueChange = onValueChange,
        modifier = modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(29.dp))

    // TODO: 遷移先URL
    PasswordForgotLink(
        text = stringResource(R.string.common__password_forgot),
        linkedText = stringResource(R.string.common__password_forgot_link),
        link = passwordForgotLink,
        onLinkClick = {},
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(21.dp))
}

