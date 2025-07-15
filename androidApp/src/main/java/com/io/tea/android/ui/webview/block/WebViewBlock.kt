package com.io.tea.android.ui.webview.block

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.KeyEvent
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebViewClientCompat

@SuppressLint("SetjavaScriptEnabled")
@Composable
fun WebViewBlock(
    url: String,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    webViewClient: WebViewClientCompat = remember { WebViewClientCompat() },
) {
    AndroidView(
        factory = { context ->
            val view = WebView(context)
            view.loadUrl(
                url,
                mapOf(),
            )
            view.settings.javaScriptEnabled = true
            view.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN && view.canGoBack()) {
                    view.goBack()
                }
                true
            }
            view.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
            view.setBackgroundColor(Color.TRANSPARENT)
            return@AndroidView view
        },
        modifier = modifier.padding(contentPadding),
        update = { view ->
            view.webViewClient = webViewClient
        },
    )
}