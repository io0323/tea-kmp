package com.io.tea.android.ui.notice.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.notice.common.NoticeConverter
import com.io.tea.android.ui.notice.common.NoticeModel
import com.io.tea.android.ui.notice.detail.NoticeDetailDestination
import com.io.tea.android.ui.notice.list.state.NoticeListViewModelUiState
import com.io.tea.domain.usecase.FetchNoticeListUseCase
import com.io.tea.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoticeListViewModel(
    application: Application,
    private val fetchNoticeListUseCase: FetchNoticeListUseCase,
) : AndroidViewModel(application) {

    private val _noticeListStateFlow: MutableStateFlow<List<NoticeModel>> =
        MutableStateFlow(emptyList())
    val noticeListStateFlow: StateFlow<List<NoticeModel>> = _noticeListStateFlow.asStateFlow()

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _uiStateFlow = MutableStateFlow(
        NoticeListViewModelUiState(
            noticeList = listOf(),
            isLoad = true,
            isRefresh = false,
            isError = false,
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        fetchNoticeList()
    }

    private fun fetchNoticeList() {
        viewModelScope.launch {
            when (val result = fetchNoticeListUseCase()) {
                is UseCaseResult.Success -> {
                    _noticeListStateFlow.value = result.data.map {
                        NoticeConverter.convert(it)
                    }
                    _uiStateFlow.update {
                        it.copy(
                            isLoad = false,
                        )
                    }
                }

                is UseCaseResult.Failure -> {
                    _uiStateFlow.update {
                        it.copy(
                            isError = true,
                        )
                    }
                }
            }
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    fun onClickNoticeItem(notice: NoticeModel) {
        _navigationStateFlow.value = NoticeDetailDestination(
            id = ""
        )
    }
}
