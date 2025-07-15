package com.io.tea.android.ui.charge.qr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.common.component.model.BarcodeBackgroundModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ChargeQrScanPage(
    viewModel: ChargeQrScanViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle(lifecycleOwner)

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    val barcodeBackgroundModel = BarcodeBackgroundModel(
        rectangleOffsetX = 114.dp,
        rectangleOffsetY = 180.dp,
        cornerLength = 16.dp,
        cornerStrokeWidth = 4.dp,
        cornerColor = TeaAppTheme.colors.White,
        backgroundColor = TeaAppTheme.colors.Black40
    )

    ChargeQrScanScreen(
        uiState = uiState,
        barcodeBackgroundModel = barcodeBackgroundModel,
        onQrCodeDetected = viewModel::onQrCodeDetected,
        onRationaleConfirmClick = viewModel::onRationaleConfirmClick,
        onDeniedConfirmClick = viewModel::onDeniedConfirmClick,
        onDeniedDismissClick = viewModel::onDeniedDismissClick,
        onCloseClick = viewModel::onCloseClick,
        onManuallyButtonClick = viewModel::onManuallyButtonClick
    )
}
