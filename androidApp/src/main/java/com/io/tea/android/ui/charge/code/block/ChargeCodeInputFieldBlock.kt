package com.io.tea.android.ui.charge.code.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.charge.code.INPUT_DIGITS
import com.io.tea.android.ui.charge.code.INPUT_FORMS
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.PlaceholderTextField

@Composable
internal fun ChargeCodeInputFieldBlock(
    onValueChange: (List<String>) -> Unit,
) {
    val focusRequesters = remember {
        (0 until INPUT_FORMS).map { FocusRequester() }
    }
    var chargeCodes by remember { mutableStateOf(List(INPUT_FORMS) { "" }) }
    val strokeWidth: Dp = 1.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .background(TeaAppTheme.colors.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(INPUT_FORMS) { index ->
            PlaceholderTextField(
                value = chargeCodes[index],
                onValueChange = { newValue ->
                    val regex = Regex("^[a-z0-9]*$")
                    if (newValue.length <= INPUT_DIGITS && regex.matches(newValue)) {
                        chargeCodes = chargeCodes.toMutableList().also {
                            it[index] = newValue
                        }
                        onValueChange(chargeCodes)
                        if (newValue.isEmpty() && index > 0) {
                            focusRequesters[index - 1].requestFocus()
                        } else if (newValue.length == INPUT_DIGITS && index < (INPUT_FORMS - 1)) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = TeaAppTheme.typography.h4.fontSize,
                ),
                placeholder = stringResource(id = R.string.charge_code_input__placeholder),
                modifier = Modifier
                    .weight(1f)
                    .height(34.dp)
                    .background(color = TeaAppTheme.colors.White)
                    .focusRequester(focusRequesters[index])
                    .drawBehind {
                        val strokeWidthPx = strokeWidth.toPx()
                        val y = size.height - strokeWidthPx / 2
                        drawLine(
                            color = Colors.Base.primary,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = strokeWidthPx,
                        )
                    }
                    .align(Alignment.CenterVertically)
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Backspace && keyEvent.type == KeyEventType.KeyUp) {
                            val previousIndex = index - 1
                            if (index == chargeCodes.size - 1) {
                                if (chargeCodes[index].isEmpty() && previousIndex >= 0) {
                                    focusRequesters[previousIndex].requestFocus()
                                } else {
                                    if (chargeCodes[index].isEmpty()) {
                                        focusRequesters[previousIndex].requestFocus()
                                    }
                                }
                            } else if (index > 0) {
                                if (chargeCodes[index].isEmpty()) {
                                    focusRequesters[previousIndex].requestFocus()
                                }
                            }
                        }
                        false
                    },
                KeyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = if (chargeCodes.last().length == INPUT_DIGITS
                        && index == (INPUT_FORMS - 1)
                    ) ImeAction.Done else ImeAction.Next
                ),
            )
            Spacer(modifier = Modifier.width(7.dp))
        }
    }
}
