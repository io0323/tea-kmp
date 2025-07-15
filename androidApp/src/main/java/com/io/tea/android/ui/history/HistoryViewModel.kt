package com.io.tea.android.ui.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.common.component.model.TabButtonItem
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.ui.history.model.TransactionHistoryConverter
import com.io.tea.android.ui.history.model.TransactionHistoryType
import com.io.tea.android.ui.history.model.TransactionHistoryWithHeaderModel
import com.io.tea.android.ui.history.state.TransactionHistory
import com.io.tea.android.ui.history.state.TransactionHistoryUiState
import com.io.tea.android.ui.history.state.TransactionHistoryUseCaseState
import com.io.tea.android.util.DateUtil
import com.io.tea.android.util.StringUtil
import com.io.tea.domain.usecase.FetchRegionDetailsUseCase
import com.io.tea.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

@RequiresApi(Build.VERSION_CODES.O)
class HistoryViewModel(
    // TODO : (Dummy)取引履歴＆Lot有効期限のUseCaseを使用すること
    private val getTransactionHistoryUseCase: FetchRegionDetailsUseCase
) : ViewModel(), KoinComponent {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _tabListStateFlow = MutableStateFlow<List<TabButtonItem>>(emptyList())
    val tabListStateFlow: MutableStateFlow<List<TabButtonItem>> = _tabListStateFlow

    private val _totalPointStateFlow = MutableStateFlow("0")
    val totalPointStateFlow: StateFlow<String> = _totalPointStateFlow

    private val _useCaseStateFlow: MutableStateFlow<TransactionHistoryUseCaseState> =
        MutableStateFlow(TransactionHistoryUseCaseState.Initial)
    val useCaseStateFlow: StateFlow<TransactionHistoryUseCaseState> =
        _useCaseStateFlow.asStateFlow()

    private val _uiStateFlow = MutableStateFlow(
        TransactionHistoryUiState(
            isLoad = false
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        _tabListStateFlow.value = listOf(
            TabButtonItem(1, R.string.transaction_history__tab_all),
            TabButtonItem(2, R.string.transaction_history__tab_charge),
            TabButtonItem(3, R.string.transaction_history__tab_payment),
        )
        initTransactionHistoryTabListFirstSelect()
        fetchTransactionHistory("123456789")
    }

    private fun initTransactionHistoryTabListFirstSelect() {
        val deletePayList = _tabListStateFlow.value
        if (deletePayList.isNotEmpty()) {
            val firstItem = deletePayList.first()
            firstItem.isSelected = true
            _tabListStateFlow.value = deletePayList
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchTransactionHistory(id: String) {
        viewModelScope.launch {
            _useCaseStateFlow.value = TransactionHistoryUseCaseState.Loading

            when (val result = getTransactionHistoryUseCase(regionId = id.toLong())) {
                is UseCaseResult.Success -> {
                    // TODO : DummyData
                    val point = "1000000"
                    val headerYearMonthList = listOf(
                        "202412",
                        "202411",
                        "202410",
                    )
                    val transactionHistoryCharge = TransactionHistory(
                        regionId = result.data.id,
                        storeName = "Lot One",
                        useDate = "20240205",
                        transactionNumber = "0000000000",
                        transactionDate = "20240206",
                        point = "1000000",
                        pointGray = "2000",
                        upDown = "UP",
                        transactionType = TransactionHistoryType.CHARGE
                    )
                    val transactionHistoryPayment = TransactionHistory(
                        regionId = result.data.id,
                        storeName = "Lot Two(02_01)",
                        useDate = "20240205",
                        transactionNumber = "0000000000",
                        transactionDate = "20240206",
                        point = "1000000",
                        pointGray = "2000",
                        upDown = "Down",
                        transactionType = TransactionHistoryType.PAYMENT
                    )
                    val transactionHistoryRepayment = TransactionHistory(
                        regionId = result.data.id,
                        storeName = "Lot Three(03_01)",
                        useDate = "20240205",
                        transactionNumber = "0000000000",
                        transactionDate = "20240206",
                        point = "1000000",
                        pointGray = "2000",
                        upDown = "UP",
                        transactionType = TransactionHistoryType.REPAYMENT
                    )
                    val transactionHistoryWithHeaderList =
                        headerYearMonthList.map {
                            TransactionHistoryWithHeaderModel(
                                headerYearMonth = DateUtil.toFormatYM(
                                    date = it,
                                    pattern = "yyyy年M月"
                                ),
                                transactionHistoryList = listOf(
                                    TransactionHistoryConverter.convert(transactionHistoryCharge),
                                    TransactionHistoryConverter.convert(transactionHistoryPayment),
                                    TransactionHistoryConverter.convert(transactionHistoryRepayment),
                                )
                            )
                        }
                    _totalPointStateFlow.value = StringUtil.formatComma(point.toLong())
                    _useCaseStateFlow.value =
                        TransactionHistoryUseCaseState.Success(transactionHistoryWithHeaderList)
                }

                is UseCaseResult.Failure -> {
                    _useCaseStateFlow.value =
                        TransactionHistoryUseCaseState.Failure(result.errorMessage)
                }
            }
        }
    }

    fun onClickTab(selectedItem: TabButtonItem) {
        _tabListStateFlow.update { list ->
            list.map {
                if (it.id == selectedItem.id) {
                    it.copy(
                        isSelected = true
                    )
                } else {
                    it.copy(
                        isSelected = false
                    )
                }
            }
        }
    }

    fun onClickPaySwitch() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onPopBack() {
        _navigationStateFlow.value = HomeDestination("")
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}