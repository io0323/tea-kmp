package com.io.tea.android.ui.common.block

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.AppTextField

@Composable
internal fun EmailFormBlock(
    modifier: Modifier = Modifier,
    defaultText: String = "",
    isError: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource,
    onValueChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf(defaultText) }

    Text(
        text = stringResource(R.string.common__email_form),
        style = TeaAppTheme.typography.label,
        modifier = modifier
    )

    AppTextField(
        text = text,
        placeholder = placeholder,
        onTextFieldClick = {},
        onValueChange = {
            text = it
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
        ),
        interactionSource = interactionSource,
        isError = isError,
    )
    Spacer(modifier = Modifier.height(21.dp))
}