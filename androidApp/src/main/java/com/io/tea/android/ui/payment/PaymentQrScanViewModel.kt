package com.io.tea.android.ui.payment

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.region.list.MyTeaListDestination
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PaymentQrScanViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _uiStateFlow: MutableStateFlow<PaymentQrScanUiState> =
        MutableStateFlow(PaymentQrScanUiState())
    val uiStateFlow: StateFlow<PaymentQrScanUiState> = _uiStateFlow

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onQrCodeDetected(result: String) {
        Napier.d("qr: $result")
        // TODO: 遷移先は要変更
        _navigationStateFlow.value = MyTeaListDestination(result)
    }

    fun onRationaleConfirmClick(requestPermission: () -> Unit) {
        _uiStateFlow.update { it.copy(showPermissionDeniedDialog = false) }
        requestPermission()
        _uiStateFlow.update { it.copy(showPermissionDeniedDialog = true) }
    }

    fun onDeniedConfirmClick() {
        // TODO: 設定画面へ遷移
        _uiStateFlow.update { it.copy(showPermissionDeniedDialog = false) }
    }

    fun onDeniedDismissClick() {
        // TODO: Lotトップへ遷移
        _uiStateFlow.update { it.copy(showPermissionDeniedDialog = false) }
    }

    fun onCloseClick() {
        _navigationStateFlow.value = PopBackDestination
    }
}

data class PaymentQrScanUiState(
    val showPermissionDeniedDialog: Boolean = true,
)
