package com.io.tea.android.ui.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.io.tea.android.ui.common.component.TextButton
import com.io.tea.android.ui.common.component.model.PermissionAlertModel

@Composable
internal fun TeaAppPermissionDeniedDialog(
    permissionAlertModel: PermissionAlertModel
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = stringResource(permissionAlertModel.titleId)
            )
        },
        text = {
            Text(
                text = stringResource(permissionAlertModel.descriptionId)
            )
        },
        confirmButton = {
            TextButton(
                textResId = permissionAlertModel.confirmButtonId,
                isEnabled = true,
                onClick = {
                    permissionAlertModel.onConfirmClick()
                }
            )
        },
        dismissButton = permissionAlertModel.dismissButtonId?.let {
            {
                TextButton(
                    textResId = it,
                    isEnabled = true,
                    onClick = {
                        permissionAlertModel.onDismissClick()
                    }
                )
            }
        }
    )
}
