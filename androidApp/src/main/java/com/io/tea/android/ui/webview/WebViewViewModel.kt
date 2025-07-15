package com.io.tea.android.ui.webview

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.webview.model.Domain
import com.io.tea.android.ui.webview.model.WebDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class WebViewViewModel(
    webUrl: String,
    private val webTitle: String?,
) : ViewModel(), KoinComponent {

    val url by lazy { webUrl }

    private val _webDomainModelStateFlow =
        MutableStateFlow<WebDomainModel>(WebDomainModel.Init)
    internal val webDomainModelStateFlow: StateFlow<WebDomainModel> =
        _webDomainModelStateFlow.asStateFlow()

    private val _webTitleStateFlow = MutableStateFlow(webTitle ?: "")
    val webTitleStateFlow: StateFlow<String> = _webTitleStateFlow.asStateFlow()

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    internal val webViewClient = WebViewPageWebViewClient(this)

    init {
        _webDomainModelStateFlow.value = WebDomainModel.Loaded(
            url = this.url,
            // TODO : authSession ログイン済みか？を持たせる想定
            authSession = false,
        )

        viewModelScope.launch {
            _webDomainModelStateFlow.value =
                (_webDomainModelStateFlow.value as? WebDomainModel.Loaded)?.copy(
                    authSession = true
                ) ?: WebDomainModel.Init
        }
    }

    fun onPopBack() {
        if (_webDomainModelStateFlow.value is WebDomainModel.Loaded) {
            _navigationStateFlow.value = PopBackDestination
        }
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }

    internal fun onPageFinished(view: WebView, url: String) {
        val title = view.title
        if (title != null && url.endsWith(title)) return
        if (_webDomainModelStateFlow.value is WebDomainModel.Loaded) {
            _webTitleStateFlow.value = this.webTitle ?: title ?: ""
        }
    }

    internal fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return when {
            // TODO : UriPath
            request.url.host?.endsWith(Domain) == true -> {
                // Note : AlertDialog, Destination, DeepLink etc
                false
            }

            else -> {
                // NOTE : 他ドメインの場合はWebViewリンク処理を中断する
                true
            }
        }
    }

    internal fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
        val initialUri = try {
            Uri.parse(this.url)
        } catch (e: Exception) {
            return
        }

        val destinationUri = try {
            Uri.parse(url)
        } catch (e: Exception) {
            return
        }
        // TODO : UriPath (更新後等に遷移元に戻る際に使用する想定)
        if (initialUri.path == "initPath" && destinationUri.path == "destinationPath") {
            _navigationStateFlow.value = PopBackDestination
        }
    }
}
