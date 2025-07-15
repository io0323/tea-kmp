package com.io.tea.android.ui.common

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

@SuppressLint("ModifierParameter")
@Composable
internal fun TeaAppTextFieldLabel(
    @StringRes resourceId: Int,
    textStyle: TextStyle = TeaAppTheme.typography.label,
    isRequired: Boolean = true,
    modifierRow: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifierRow
    ) {
        Text(
            text = stringResource(resourceId),
            style = textStyle,
            modifier = Modifier.padding(end = 4.dp)
        )
        if (isRequired) {
            RequiredText()
        }
    }
}

@Composable
internal fun RequiredText() {
    Box(
        modifier = Modifier
            .background(
                color = Colors.LabelBackground,
                shape = RoundedCornerShape(2.dp)
            )
            .padding(
                horizontal = 5.dp,
                vertical = 2.dp
            )
    ) {
        Text(
            text = stringResource(R.string.user_information_input__required_label),
            style = TeaAppTheme.typography.label,
            color = Colors.FontContrast,
        )
    }
}