package com.io.tea.android.ui.charge.confirm

import androidx.lifecycle.ViewModel
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.charge.code.ChargeCodeInputDestination
import com.io.tea.android.ui.charge.complete.ChargeCompleteDestination
import com.io.tea.android.ui.charge.confirm.model.ChargeCodeConfirmModel
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.util.ColorUtil
import com.io.tea.android.util.StringUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ChargeCodeConfirmViewModel : ViewModel() {
    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _chargeCodeConfirmModelStateFlow = MutableStateFlow(ChargeCodeConfirmModel.default)
    val chargeCodeConfirmModelStateFlow: StateFlow<ChargeCodeConfirmModel> =
        _chargeCodeConfirmModelStateFlow

    init {
        // TODO: テストデータ
        val chargePoint = 1000000L
        val backgroundColorCode = "#EA613B"
        val period = "※ 有効期限は2030年12月31日です。\n期限内にご利用ください。"
        _chargeCodeConfirmModelStateFlow.update {
            it.copy(
                backgroundColor = ColorUtil.hexToColor(backgroundColorCode),
                chargePoint = "+${StringUtil.formatComma(chargePoint)}",
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

    fun onModifyClick() {
        // TODO:
        _navigationStateFlow.value = ChargeCodeInputDestination()
    }

    fun onFixClick() {
        // TODO:
        _navigationStateFlow.value = ChargeCompleteDestination()
    }
}
