package com.io.tea.android.ui.account.auth.sms.block

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

// TODO: 認証コード(smsAuthCodes)やフォーカス位置を決めるロジックは、ViewModelに移す予定。
@Composable
fun AuthSmsBlock(
    smsAuthCodeLength: Int = 6,
    isPasswordMode: Boolean = false,
    isInputFormClear: Boolean = false,
    onValueChange: (String) -> Unit = {}, // NOTE : 全入力でボタンを活性化させるケースetcで使用
    onInputComplete: (String) -> Unit = {}, // NOTE : 全入力でActionさせるケースで使用 (Unit改善余地あり)
    onInputFormClear: (() -> Unit?)? = null,
) {
    var smsAuthCodes by remember { mutableStateOf(List(smsAuthCodeLength) { "" }) }
    val focusRequesters = remember(smsAuthCodeLength) { List(smsAuthCodeLength) { FocusRequester() } }
    val keyboardType = when {
        isPasswordMode -> KeyboardType.NumberPassword
        else -> KeyboardType.Number
    }
    val visualTransformation = when {
        isPasswordMode -> PasswordVisualTransformation()
        else -> VisualTransformation.None
    }
    val textStyle = when {
        isPasswordMode -> TeaAppTheme.typography.h1.copy(textAlign = TextAlign.Center)
        else -> TeaAppTheme.typography.label.copy(textAlign = TextAlign.Center)
    }
    if (isInputFormClear) {
        smsAuthCodes = List(smsAuthCodeLength) { "" }
        focusRequesters.first().requestFocus()
        onInputFormClear?.invoke()
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        repeat(smsAuthCodeLength) { index ->
            TextField(
                value = smsAuthCodes[index],
                onValueChange = { value ->
                    if (value.toIntOrNull() != null && smsAuthCodes[index].isEmpty()) {
                        smsAuthCodes = smsAuthCodes.toMutableList().also {
                            it[index] = value
                        }
                        focusRequesters.last().requestFocus()
                        if (value.isNotEmpty() && index < smsAuthCodeLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                        if (smsAuthCodes.all { it.isNotEmpty() }) {
                            onInputComplete(smsAuthCodes.joinToString(""))
                        }
                        onValueChange(smsAuthCodes.joinToString(""))
                    }
                },
                modifier = Modifier
                    .width(40.dp)
                    .height(56.dp)
                    .border(
                        1.dp,
                        Colors.Grey500,
                        RoundedCornerShape(4.dp)
                    )
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Backspace && keyEvent.type == KeyEventType.KeyUp) {
                            val previousIndex = index - 1
                            if (index == smsAuthCodeLength - 1) {
                                // 最後尾の場合
                                if (smsAuthCodes[index].isEmpty() && previousIndex >= 0) {
                                    smsAuthCodes = smsAuthCodes
                                        .toMutableList()
                                        .also { it[previousIndex] = "" }
                                    focusRequesters[previousIndex].requestFocus()
                                } else {
                                    // 空ではない場合は削除のみで、カーソル移動しない
                                    smsAuthCodes = smsAuthCodes
                                        .toMutableList()
                                        .also { it[index] = "" }
                                }
                            } else if (index > 0) {
                                // 2桁目〜最後尾の1つ前まで
                                smsAuthCodes = smsAuthCodes
                                    .toMutableList()
                                    .also {
                                        it[previousIndex] = ""
                                        it[index] = ""
                                    }
                                focusRequesters[previousIndex].requestFocus()
                            } else {
                                // 1桁目の場合
                                smsAuthCodes = smsAuthCodes
                                    .toMutableList()
                                    .also { it[index] = "" }
                            }
                            onValueChange(smsAuthCodes.joinToString(""))
                        }
                        false
                    },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType
                ),
                visualTransformation = visualTransformation,
                textStyle = textStyle,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Colors.FontContrast,
                    unfocusedContainerColor = Colors.FontContrast,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
        }
    }
}
