package com.io.tea.android.ui.payment.point

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.common.component.model.PaymentPointInputModel
import com.io.tea.android.ui.payment.confirm.PaymentPointConfirmDestination
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.StringUtil
import com.io.tea.android.util.StringUtil.toIntSafe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PaymentPointInputViewModel : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _paymentPointInputModelStateFlow = MutableStateFlow(PaymentPointInputModel.default)
    val paymentPointInputModelStateFlow: StateFlow<PaymentPointInputModel> =
        _paymentPointInputModelStateFlow

    init {
        // TODO: サンプルデータ
        val regionName = "モLot Three"
        val availablePoint = 5000L
        val backgroundColorCode = "#EA613B"

        _paymentPointInputModelStateFlow.update {
            it.copy(
                regionName = regionName,
                availablePoint = StringUtil.formatComma(availablePoint),
                backgroundColor = ColorUtil.hexToColor(backgroundColorCode),
            )
        }
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onCloseClick() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onInputPointChanged(point: String) {
        _paymentPointInputModelStateFlow.update {
            it.copy(
                inputPoint = point, // 3桁区切りカンマは、InputPointFieldで行うので不要
                isEnable = point.toIntSafe() > 0,
            )
        }
    }

    fun onClickNext() {
        _navigationStateFlow.value = PaymentPointConfirmDestination("")
    }
}
