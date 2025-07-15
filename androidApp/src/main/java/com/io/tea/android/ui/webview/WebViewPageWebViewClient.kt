package com.io.tea.android.ui.webview

import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.webkit.WebViewClientCompat

internal class WebViewPageWebViewClient(
    private val viewModel: WebViewViewModel,
) : WebViewClientCompat() {
    override fun onPageFinished(view: WebView, url: String) {
        return viewModel.onPageFinished(view, url)
    }

    override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
        return viewModel.doUpdateVisitedHistory(view, url, isReload)
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return viewModel.shouldOverrideUrlLoading(view, request)
    }
}
