package com.io.tea.android.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.RightEndButton
import com.io.tea.android.ui.common.component.model.ButtonIconType

@SuppressLint("ModifierParameter")
@Composable
fun PaySwitch(
    onClick: () -> Unit,
    buttonModifier: Modifier
) {
    RightEndButton(
        isEnabled = true,
        width = 69.dp,
        height = 46.dp,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            bottomStart = 16.dp,
            topEnd = 0.dp,
            bottomEnd = 0.dp
        ),
        contentPadding = PaddingValues(start = 10.dp, top = 12.dp, bottom = 10.dp),
        textResId = R.string.common__pay_switch,
        textStyle = TeaAppTheme.typography.footerNav,
        textResColor = TeaAppTheme.colors.Grey900,
        textAlign = TextAlign.Center,
        maxLines = 2,
        buttonModifier = buttonModifier,
        iconType = ButtonIconType.LEFT,
        icon = android.R.drawable.ic_menu_close_clear_cancel,
        tintRes = TeaAppTheme.colors.Grey900,
        onClick = onClick,
    )
}
