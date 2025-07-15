package com.io.tea.android.ui.payment.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.MainApplication
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.payment.complete.PaymentCompleteDestination
import com.io.tea.android.ui.payment.confirm.model.PaymentPointConfirmModel
import com.io.tea.android.ui.payment.confirm.model.PointDigits
import com.io.tea.android.ui.payment.point.PaymentPointInputDestination
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.ImageUtil
import com.io.tea.android.util.StringUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class PaymentPointConfirmViewModel : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _paymentPointConfirmModelStateFlow =
        MutableStateFlow(PaymentPointConfirmModel.default)
    val paymentPointConfirmModelStateFlow: StateFlow<PaymentPointConfirmModel> =
        _paymentPointConfirmModelStateFlow

    companion object {
        private const val TOTAL_REMAINING_MILLIS = 5 * 60 * 1000L
        private const val DELAY_MILLIS = 1000L
        private const val REMAINING_TIME_FORMAT = "%02d:%02d"
    }

    init {
        // TODO: テストデータ
        val backgroundColorCode = "#EA613B"
        val regionName = "橿原市 くらし応援Lot券"
        val imageUrl = ImageUtil.getDrawablePath(
            context = MainApplication.appContext,
            drawablePath = "${R.drawable.hangryangry}"
        )
        val storeName = "Lot Two"
        val point = 10000L

        _paymentPointConfirmModelStateFlow.update {
            it.copy(
                backgroundColor = ColorUtil.hexToColor(backgroundColorCode),
                regionName = regionName,
                imageUrl = imageUrl,
                storeName = storeName,
                point = StringUtil.formatComma(point),
                pointDigits = PointDigits.fromValue(point),
            )
        }
        startTimer()
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onModifyClick() {
        // TODO: 修正 「通常Scan｜払い出しLot入力」へ遷移
        _navigationStateFlow.value = PaymentPointInputDestination()
    }

    fun onFixClick() {
        // TODO: 確定 「払い出し完了」へ遷移
        _navigationStateFlow.value = PaymentCompleteDestination()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (true) {
                var currentTime = TOTAL_REMAINING_MILLIS
                while (currentTime >= 0) {
                    val minutes = TimeUnit.MILLISECONDS.toMinutes(currentTime)
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(currentTime) % 60

                    _paymentPointConfirmModelStateFlow.update {
                        it.copy(
                            remainingTime = REMAINING_TIME_FORMAT.format(minutes, seconds)
                        )
                    }
                    delay(DELAY_MILLIS)
                    currentTime -= DELAY_MILLIS
                }
            }
        }
    }
}
