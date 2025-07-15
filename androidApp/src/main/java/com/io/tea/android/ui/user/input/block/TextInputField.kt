package com.io.tea.android.ui.user.input.block

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.AppTextField
import com.io.tea.android.ui.common.NumericTextField
import com.io.tea.android.ui.common.TeaAppTextFieldLabel

@Composable
internal fun BirthBlock(
    text: String = "",
    onTextFieldClick: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    TeaAppTextFieldLabel(
        resourceId = R.string.user_information_input__birth,
        modifierRow = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(4.dp))
    // TODO: 生年月選択ダイアログの表示
    AppTextField(
        text = text,
        onValueChange = {},
        enabled = false,
        onTextFieldClick = onTextFieldClick,
        interactionSource = interactionSource,
        placeholder = {
            Text(
                text = stringResource(R.string.user_information_input__birth_hint),
                style = TeaAppTheme.typography.h4
            )
        },
    )
}

@Composable
internal fun GenderBlock(
    text: String = "",
    onTextFieldClick: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    TeaAppTextFieldLabel(
        R.string.user_information_input__gender,
        modifierRow = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(4.dp))
    // TODO: 性別選択ダイアログの表示
    AppTextField(
        text = text,
        onValueChange = {},
        enabled = false,
        onTextFieldClick = onTextFieldClick,
        interactionSource = interactionSource,
        placeholder = {
            Text(
                text = stringResource(R.string.user_information_input__gender_hint),
                style = TeaAppTheme.typography.h4
            )
        },
    )
}

@Composable
internal fun ZipBlock(
    text: String = "",
    maxLength: Int = 8,
    isSearchButtonEnabled: Boolean = false,
    onSearchButtonClick: () -> Unit,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TeaAppTextFieldLabel(
                R.string.user_information_input__zip,
                modifierRow = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            NumericTextField(
                defaultText = text,
                maxLength = maxLength,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = stringResource(R.string.user_information_input__zip_hint),
                        style = TeaAppTheme.typography.h4
                    )
                },
                onValueChange = onValueChange,
            )
        }

        // TODO: 第２フェーズで有効化の予定
        if (isSearchButtonEnabled) {
            Spacer(modifier = Modifier.width(16.dp))
            SearchAddressButton(
                onClick = onSearchButtonClick
            )
        }
    }
}

@Composable
internal fun AddressBlock(
    text: String = "",
    interactionSource: MutableInteractionSource
) {
    TeaAppTextFieldLabel(
        resourceId = R.string.user_information_input__address,
        isRequired = false,
        modifierRow = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(4.dp))
    AppTextField(
        text = text,
        onValueChange = {},
        onTextFieldClick = {},
        interactionSource = interactionSource,
        placeholder = {
            Text(
                text = stringResource(R.string.user_information_input__address_hint),
                style = TeaAppTheme.typography.h4
            )
        },
    )
}

@Composable
private fun SearchAddressButton(
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Colors.Grey500
        )
    ) {
        Text(
            text = stringResource(R.string.user_information_input__address_button),
            color = Colors.FontPrimary,
            style = TeaAppTheme.typography.btn
        )
    }
}
