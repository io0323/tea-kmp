package com.io.tea.android.ui.payment

import android.Manifest
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.io.tea.android.ui.common.TeaAppBarcodeCamera
import com.io.tea.android.ui.common.TeaAppRuntimeSinglePermission
import com.io.tea.android.ui.common.block.PermissionDeniedBlock
import com.io.tea.android.ui.common.component.model.BarcodeBackgroundModel
import com.io.tea.android.ui.payment.block.CloseButtonBlock
import com.io.tea.android.ui.payment.block.QrScandescriptionBlock

@OptIn(ExperimentalGetImage::class)
@Composable
internal fun PaymentQrScanScreen(
    uiState: PaymentQrScanUiState,
    barcodeBackgroundModel: BarcodeBackgroundModel,
    onQrCodeDetected: (String) -> Unit,
    onRationaleConfirmClick: (() -> Unit) -> Unit,
    onDeniedConfirmClick: () -> Unit,
    onDeniedDismissClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val camera = remember { TeaAppBarcodeCamera() }

    Scaffold(
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            TeaAppRuntimeSinglePermission(
                permission = Manifest.permission.CAMERA,
                grantedContent = {
                    camera.CameraPreview(
                        barcodeBackgroundModel = barcodeBackgroundModel,
                        onBarcodeScanned = { barcode ->
                            camera.stopCamera()
                            onQrCodeDetected(barcode)
                        },
                        onTargetDrawn = {}
                    )

                    QrScandescriptionBlock()
                    CloseButtonBlock(onCloseClick)
                },
                deniedContent = { shouldShowRationale, requestPermission ->
                    if (uiState.showPermissionDeniedDialog) {
                        PermissionDeniedBlock(
                            shouldShowRationale,
                            onRationaleConfirmClick,
                            requestPermission,
                            onDeniedConfirmClick,
                            onDeniedDismissClick
                        )
                    }
                }
            )
        }
    }
}

