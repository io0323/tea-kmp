package com.io.tea.android.ui.charge.complete

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.charge.complete.model.ChargeCompleteModel
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.StringUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ChargeCompleteViewModel : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _chargeCompleteModelStateFlow = MutableStateFlow(ChargeCompleteModel.default)
    val chargeCompleteModelStateFlow: StateFlow<ChargeCompleteModel> =
        _chargeCompleteModelStateFlow

    init {
        // TODO: テストデータ
        val backgroundColorCode = "#EA613B"
        val plusPoint = 1000000L
        val totalPoint = 2500000L
        val period = "※ 有効期限は2030年12月31日です。\n期限内にご利用ください。"
        _chargeCompleteModelStateFlow.update {
            it.copy(
                backgroundColor = ColorUtil.hexToColor(backgroundColorCode),
                plusPoint = "+${StringUtil.formatComma(plusPoint)}",
                totalPoint = StringUtil.formatComma(totalPoint),
                period = period,
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
