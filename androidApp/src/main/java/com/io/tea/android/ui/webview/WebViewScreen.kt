package com.io.tea.android.ui.webview

import android.webkit.CookieManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.webkit.WebViewClientCompat
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.webview.block.WebViewBlock
import com.io.tea.android.ui.webview.model.WebDomainModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun WebViewScreen(
    viewModel: WebViewViewModel = koinViewModel(),
    webTitle: String,
    webDomainModel: WebDomainModel,
    webViewClient: WebViewClientCompat,
) {
    val cookieManager = remember { CookieManager.getInstance() }
    // TODO : setCookie Param
    LaunchedEffect(cookieManager) {
        cookieManager.setCookie("https://www.google.com", "path=/")
    }

    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            when (webDomainModel) {
                is WebDomainModel.Loaded -> {
                    webTitle
                }

                else -> {
                    ""
                }
            }.also {
                TopBar(
                    title = it,
                    onPopBack = { viewModel.onPopBack() },
                )
            }
        },
        bottomBar = {},
    ) { contentPadding ->
        if (webDomainModel is WebDomainModel.Loaded) {
            if (webDomainModel.authSession && webDomainModel.isDomain) {
                WebViewBlock(
                    url = webDomainModel.url,
                    contentPadding = contentPadding,
                    modifier = Modifier.fillMaxSize(),
                    webViewClient = webViewClient,
                )
            } else {
                // TODO : 一旦前画面に戻す
                viewModel.onPopBack()
            }
        }
    }
}
