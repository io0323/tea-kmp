package com.io.tea.android.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonPrimary
import com.io.tea.android.ui.common.component.ButtonSecondary

@Composable
fun PaymentDuplicateWarningDialog(
    isShowDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (isShowDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = TeaAppTheme.colors.White, RoundedCornerShape(20.dp))
                    .shadow(elevation = 1.dp)
                    .padding(horizontal = 20.dp, vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = "caution",
                        tint = TeaAppTheme.colors.BlueDark,
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = R.string.payment_duplicate_warning__title),
                        style = TeaAppTheme.typography.h3,
                        color = TeaAppTheme.colors.FontPrimary,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(id = R.string.payment_duplicate_warning__text),
                        style = TeaAppTheme.typography.h6,
                        color = TeaAppTheme.colors.FontPrimary,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    ButtonPrimary(
                        isEnabled = true,
                        textResId = R.string.payment_duplicate_warning__confirm,
                        buttonModifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        onClick = onConfirm,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ButtonSecondary(
                        isEnabled = true,
                        textResId = R.string.payment_duplicate_warning__dismiss,
                        buttonModifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        onClick = onDismiss,
                    )
                }
            }
        }
    }
}
