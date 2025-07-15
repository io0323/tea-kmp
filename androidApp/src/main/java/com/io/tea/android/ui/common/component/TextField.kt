package com.io.tea.android.ui.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun PlaceholderTextField(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    KeyboardOptions: KeyboardOptions,
    textStyle: TextStyle = TextStyle.Default,
    textAlign: TextAlign = TextAlign.Center,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        if (value.isEmpty() && !isFocused) {
            Text(
                text = placeholder,
                style = textStyle.copy(color = TeaAppTheme.colors.Grey500),
                textAlign = textAlign,
                modifier = modifier.padding(horizontal = 8.dp)
            )
        }
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = it.isFocused
                }
                .onKeyEvent { event ->
                    event.key == Key.Enter && event.type == KeyEventType.KeyUp
                },
            singleLine = true,
            keyboardOptions = KeyboardOptions,
        )
    }
}
