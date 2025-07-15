package com.io.tea.android.ui.codeinput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.codeinput.model.CodeInputModel
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.common.component.ButtonPrimary
import com.io.tea.android.ui.common.component.PlaceholderTextField

@Composable
internal fun CodeInputScreen(
    model: CodeInputModel,
    onPopBack: () -> Unit,
    onValueChange: (String) -> Unit,
    onClickButtonAdd: () -> Unit,
) {
    val strokeWidth: Dp = 1.dp
    val spacerHeight = when {
        model.errorText != null -> 18.dp
        else -> 40.dp
    }
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.screen__code_input),
                onPopBack = onPopBack,
            )
        },
        bottomBar = {},
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = TeaAppTheme.colors.BackgroundGray)
                    .padding(horizontal = 48.dp),
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = stringResource(R.string.code_input__text),
                    style = TeaAppTheme.typography.h4,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(R.string.code_input__sub_text),
                    style = TeaAppTheme.typography.caption,
                )
                Spacer(modifier = Modifier.height(24.dp))
                PlaceholderTextField(
                    value = model.inputCode,
                    onValueChange = { newValue ->
                        val regex = Regex("^[a-zA-Z0-9]*$")
                        if (regex.matches(newValue)) {
                            onValueChange(newValue)
                        }
                    },
                    textStyle = LocalTextStyle.current.copy(
                        fontSize = TeaAppTheme.typography.h4.fontSize,
                    ),
                    textAlign = TextAlign.Left,
                    placeholder = stringResource(id = R.string.code_input__placeholder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp)
                        .background(color = TeaAppTheme.colors.White)
                        .drawBehind {
                            val strokeWidthPx = strokeWidth.toPx()
                            val y = size.height - strokeWidthPx / 2
                            drawLine(
                                color = model.drawLineColor,
                                start = Offset(0f, y),
                                end = Offset(size.width, y),
                                strokeWidth = strokeWidthPx,
                            )
                        },
                    KeyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                )
                if (model.errorText != null) {
                    Text(
                        text = stringResource(model.errorText),
                        style = TeaAppTheme.typography.caption,
                        color = TeaAppTheme.colors.Error
                    )
                }
                Spacer(modifier = Modifier.height(spacerHeight))
                ButtonPrimary(
                    isEnabled = model.isEnable,
                    onClick = { if (model.isEnable) onClickButtonAdd() },
                    textResId = R.string.common__add,
                    buttonModifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
