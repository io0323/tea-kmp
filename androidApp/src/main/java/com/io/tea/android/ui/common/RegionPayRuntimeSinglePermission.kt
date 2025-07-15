package com.io.tea.android.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun TeaAppRuntimeSinglePermission(
    permission: String,
    grantedContent: @Composable () -> Unit,
    deniedContent: @Composable (Boolean, () -> Unit) -> Unit,
) {
    var permissionRequested by remember { mutableStateOf(false) }
    val permissionState = rememberPermissionState(
        permission = permission,
        onPermissionResult = { permissionRequested = true },
    )

    LaunchedEffect(Unit) {
        if (permissionState.status.isGranted) {
            permissionRequested = true
        } else if (permissionRequested.not()) {
            permissionState.launchPermissionRequest()
        }
    }

    if (permissionRequested) {
        when (permissionState.status) {
            is PermissionStatus.Granted -> grantedContent()
            is PermissionStatus.Denied -> deniedContent(
                permissionState.status.shouldShowRationale,
                permissionState::launchPermissionRequest
            )
        }
    }
}
