package com.io.tea.android.ui.payment.complete

import androidx.lifecycle.ViewModel
import com.io.tea.android.MainApplication
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.payment.complete.model.PaymentCompleteModel
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.ImageUtil
import com.io.tea.android.util.StringUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PaymentCompleteViewModel : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _paymentCompleteModelStateFlow = MutableStateFlow(PaymentCompleteModel.default)
    val paymentCompleteModelStateFlow: StateFlow<PaymentCompleteModel> =
        _paymentCompleteModelStateFlow

    init {
        // TODO: テストデータ
        val backgroundColorCode = "#EA613B"
        val regionName = "橿原市 くらし応援Lot券"
        val imageUrl = ImageUtil.getDrawablePath(
            context = MainApplication.appContext,
            drawablePath = "${R.drawable.hangryangry}"
        )
        val storeName = "Lot One"
        val point = 1000000L
        val acceptedDate = "2024/03/26 10:29"
        val paymentNumber = "1234567-ABCDEF-12345"
        _paymentCompleteModelStateFlow.update {
            it.copy(
                backgroundColor = ColorUtil.hexToColor(backgroundColorCode),
                regionName = regionName,
                imageUrl = imageUrl,
                storeName = storeName,
                point = StringUtil.formatComma(point),
                acceptedDate = acceptedDate,
                paymentNumber = paymentNumber,
            )
        }
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onPopBack() {
        _navigationStateFlow.value = MyTeaHomeDestination
    }

    fun onRegionTopClick() {
        _navigationStateFlow.value = MyTeaHomeDestination
    }
}
