package com.io.tea.android.ui.dialog

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.TextButton

@Composable
fun ShowAlertDialog(
    title: String? = null,
    text: String? = null,
    @StringRes
    confirmButtonText: Int? = null,
    @StringRes
    dismissButtonText: Int? = null,
    cancelOnDismiss: Boolean = true,
    onConfirmClick: () -> Unit = {},
    onDismissClick: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = {
            if (cancelOnDismiss) {
                onDismissClick()
            }
        },
        title = {
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    style = TeaAppTheme.typography.h3,
                    color = TeaAppTheme.colors.FontPrimary,
                )
            }
        },
        text = {
            if (!text.isNullOrBlank()) {
                Text(
                    text = text,
                    style = TeaAppTheme.typography.h6,
                    color = TeaAppTheme.colors.FontPrimary,
                )
            }
        },
        confirmButton = {
            if (confirmButtonText != null) {
                TextButton(
                    textResId = confirmButtonText,
                    isEnabled = true,
                    onClick = { onConfirmClick() }
                )
            }
        },
        dismissButton = {
            if (dismissButtonText != null) {
                TextButton(
                    textResId = dismissButtonText,
                    isEnabled = true,
                    onClick = { onDismissClick() }
                )
            }
        },
    )
}


@Composable
fun ConformAlertDialog() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {   },
            title = {
                Text(text = "位置情報を使いますか？")
            },
            text = {
                Text("GPSから位置情報を取得するとタイムラインを自動記録できます。")
            },
            confirmButton = {
                    Text("使う")
            },
            dismissButton = {
                    Text("キャンセル")
            },
        )
    }
}