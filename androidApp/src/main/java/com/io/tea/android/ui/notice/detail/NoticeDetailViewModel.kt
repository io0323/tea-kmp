package com.io.tea.android.ui.notice.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.notice.common.NoticeConverter
import com.io.tea.android.ui.notice.detail.model.NoticeDetailModel
import com.io.tea.android.ui.notice.detail.state.NoticeDetailViewModelUiState
import com.io.tea.android.ui.webview.WebViewDestination
import com.io.tea.domain.response.NoticeDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class NoticeDetailViewModel : ViewModel(), KoinComponent {

    private val _noticeDetailStateFlow: MutableStateFlow<NoticeDetailModel?> =
        MutableStateFlow(null)
    val noticeDetailStateFlow: StateFlow<NoticeDetailModel?> = _noticeDetailStateFlow.asStateFlow()

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _uiStateFlow = MutableStateFlow(
        NoticeDetailViewModelUiState(
            noticeList = listOf(),
            isLoad = true,
            isRefresh = false,
            isError = false,
        ),
    )
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        fetchNoticeDetail()
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    private fun fetchNoticeDetail() {
        // TODO UseCaseがら取得する想定
        viewModelScope.launch {
            val notice = NoticeDTO(
                date = "2023年1月10日",
                TeaName = "TeaApp",
                msg = "Lotに記載のQRが読み込めない場合の対応方法について",
                url = "https://www.google.com",
                colorCode = "#FF0000FF",
            )
            val message =
                "message message message message message message message message message message。\n" +
                        "\n" +
                        "＜手順＞\n" +
                        "① アプリのQR読み込み画面右下の「QRコードを読み取れない方は」をタップ\n" +
                        "② Lot券に記載の20桁のLotコードを入力\n" +
                        "③ 「Lotコードを確認」及び「Lotする」をタップ\n" +
                        "\n" +
                        "Lotサイトはこちら\n" +
                        "\n" +
                        "テスト1\n" + "\n" + "テスト2\n" + "\n" + "テスト3\n" + "\n" + "テスト4\n" + "\n" + "テスト5\n" + "\n" +
                        "テスト6\n" + "\n" + "テスト7\n" + "\n" + "テスト8\n" + "\n" + "テスト9\n" + "\n" + "テスト10\n" + "\n" +
                        "以上、何卒宜しくお願いいたします。"
            // モックデータ
            _noticeDetailStateFlow.value = NoticeConverter.convert(notice, message)
            _uiStateFlow.update {
                it.copy(
                    isLoad = false,
                )
            }
        }
    }

    fun onClickLink(url: String) {
        _navigationStateFlow.value = WebViewDestination(
            url = url,
            title = "title"
        )
    }
}