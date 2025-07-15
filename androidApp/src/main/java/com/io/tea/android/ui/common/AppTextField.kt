package com.io.tea.android.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun AppTextField(
    text: String = "",
    textStyle: TextStyle = TeaAppTheme.typography.h4,
    height: Dp = 40.dp,
    strokeWidth: Dp = 1.dp,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextFieldClick: () -> Unit,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: @Composable (() -> Unit)? = null,
    onFocusChanged: ((FocusState) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource,
    isError: Boolean = false,
) {
    val disabledIndicatorColor = if (isError) {
        TeaAppTheme.colors.Error
    } else {
        TeaAppTheme.colors.Blue
    }

    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        enabled = enabled,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .then(if (height > 0.dp) Modifier.height(height) else Modifier)
            .clickable {
                onTextFieldClick()
            }
            .drawBehind {
                val strokeWidthPx = strokeWidth.toPx()
                val y = size.height - strokeWidthPx / 2
                drawLine(
                    color = Colors.Base.primary,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidthPx
                )
            }
            .onFocusChanged { focusState ->
                onFocusChanged?.let { it(focusState) }
            },
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                enabled = false,
                singleLine = true,
                interactionSource = interactionSource,
                placeholder = placeholder,
                label = null,
                leadingIcon = null,
                trailingIcon = trailingIcon,
                supportingText = null,
                isError = false,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Colors.Font.primary,
                    disabledContainerColor = Color.Transparent,
                    disabledIndicatorColor = disabledIndicatorColor
                ),
                contentPadding = PaddingValues(
                    horizontal = 8.dp,
                    vertical = 6.dp
                )
            )
        },
        interactionSource = interactionSource
    )
}

@Composable
internal fun NumericTextField(
    defaultText: String = "",
    textStyle: TextStyle = TeaAppTheme.typography.h4,
    height: Dp = 40.dp,
    strokeWidth: Dp = 1.dp,
    trailingIcon: @Composable (() -> Unit)? = null,
    maxLength: Int = 1,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
) {
    AppTextField(
        text = defaultText,
        textStyle = textStyle,
        height = height,
        trailingIcon = trailingIcon,
        strokeWidth = strokeWidth,
        placeholder = placeholder,
        onTextFieldClick = {},
        onValueChange = {
            val regex = Regex("^[0-9]*$")
            if (it.length <= maxLength && regex.matches(it)) {
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
        isError = isError,
    )
}

@Composable
internal fun AlphanumericPasswordTextField(
    defaultText: String = "",
    isError: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource,
    onValueChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf(defaultText) }

    AppTextField(
        text = text,
        placeholder = placeholder,
        onTextFieldClick = {},
        onValueChange = {
            val regex = Regex("^[0-9a-zA-Z]*$")
            if (regex.matches(it)) {
                text = it
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            autoCorrectEnabled = false,
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
        interactionSource = interactionSource,
        isError = isError
    )
}

@Composable
fun CountryCodeField(
    onTextFieldClick: () -> Unit,
    onCountrySelected: (String) -> Unit,
    interactionSource: MutableInteractionSource,
    modifier: Modifier = Modifier,
    text: String = "",
) {
    Text(
        text = stringResource(R.string.phone_number_input__country_code),
        style = TeaAppTheme.typography.label,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(4.dp))
    AppTextField(
        text = text,
        onValueChange = onCountrySelected,
        enabled = false,
        onTextFieldClick = onTextFieldClick,
        interactionSource = interactionSource,
        placeholder = null
    )
}

@SuppressLint("ModifierParameter")
@Composable
fun PhoneNumberField(
    phoneNumber: String,
    interactionSource: MutableInteractionSource,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.phone_number_input__phone_label),
        style = TeaAppTheme.typography.label,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(4.dp))
    NumericTextField(
        defaultText = phoneNumber,
        maxLength = 14,
        placeholder = {
            Text(
                text = stringResource(R.string.phone_number_input__phone_hint),
                style = TeaAppTheme.typography.h4
            )
        },
        interactionSource = interactionSource,
        onValueChange = onValueChange,
        isError = isError,
    )
}

@SuppressLint("ModifierParameter")
@Composable
fun PasswordField(
    interactionSource: MutableInteractionSource,
    password: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    Text(
        text = stringResource(R.string.phone_number_input__password_label),
        style = TeaAppTheme.typography.label,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(4.dp))
    AlphanumericPasswordTextField(
        defaultText = password,
        isError = isError,
        placeholder = {
            Text(
                text = stringResource(R.string.phone_number_input__password_hint),
                style = TeaAppTheme.typography.h4
            )
        },
        interactionSource = interactionSource,
        onValueChange = onValueChange
    )
}
