package com.io.tea.android.ui.payment.block

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.NumericTextField
import com.io.tea.android.ui.common.component.model.CommaTransformation

@Composable
internal fun InputPointFieldBlock(
    inputPoint: String,
    availablePoint: String,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
) {
    InputPointField(
        point = inputPoint,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
    )
    Spacer(modifier = Modifier.height(8.dp))

    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            text = stringResource(R.string.payment_point_input__available_point),
            style = TeaAppTheme.typography.h6,
            color = TeaAppTheme.colors.FontSecondary,
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = availablePoint,
            style = TeaAppTheme.typography.poppins,
            color = TeaAppTheme.colors.FontPrimary,
        )
        Spacer(modifier = Modifier.width(2.dp))

        Text(
            text = stringResource(R.string.common__gram),
            style = TeaAppTheme.typography.point2,
            color = TeaAppTheme.colors.FontSecondary,
        )
    }
}

@Composable
fun InputPointField(
    point: String,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource
) {
    val textStyle = TeaAppTheme.typography.title2.copy(
        textAlign = TextAlign.End
    )

    Box(
        modifier = Modifier.width(216.dp) // Figma上208pxだが、Small Phoneで途切れる。
    ) {
        NumericTextField(
            defaultText = point,
            textStyle = textStyle,
            height = 0.dp,
            strokeWidth = 2.dp,
            maxLength = 7,
            trailingIcon = {
                Text(
                    text = stringResource(R.string.common__gram),
                    style = TeaAppTheme.typography.point1
                )
            },
            placeholder = {
                Text(
                    text = "0",
                    style = textStyle,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            interactionSource = interactionSource,
            onValueChange = onValueChange,
            visualTransformation = CommaTransformation(),
        )
    }
}
