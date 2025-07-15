package com.io.tea.android.ui.common.block

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.io.tea.android.ui.common.TeaAppPermissionDeniedDialog
import com.io.tea.android.ui.common.component.model.PermissionAlertModel

@Composable
internal fun PermissionDeniedBlock(
    shouldShowRationale: Boolean,
    onRationaleConfirmClick: (() -> Unit) -> Unit,
    requestPermission: () -> Unit,
    onDeniedConfirmClick: () -> Unit,
    onDeniedDismissClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val permissionDialogModel = if (shouldShowRationale) {
            PermissionAlertModel.Rationale.copy(
                onConfirmClick = {
                    onRationaleConfirmClick(requestPermission)
                }
            )
        } else {
            PermissionAlertModel.Denied.copy(
                onConfirmClick = onDeniedConfirmClick,
                onDismissClick = onDeniedDismissClick,
            )
        }
        TeaAppPermissionDeniedDialog(
            permissionAlertModel = permissionDialogModel,
        )
    }
}
