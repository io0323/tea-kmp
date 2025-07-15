package com.io.tea.android.ui.webview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun WebViewPage(
    url: String,
    title: String?,
    viewModel: WebViewViewModel = koinViewModel {
        parametersOf(
            url,
            title
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val webTitle by viewModel.webTitleStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val webDomainModel by viewModel.webDomainModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val navigation by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    navigation?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    WebViewScreen(
        webTitle = webTitle,
        webDomainModel = webDomainModel,
        webViewClient = viewModel.webViewClient,
    )
}
