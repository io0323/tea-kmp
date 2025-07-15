package com.io.tea.android.ui.account.auth.sms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AuthSmsPage(
    viewModel: AuthSmsViewModel = koinViewModel(),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val model by viewModel.authSmsModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }
    AuthSmsScreen(
        model = model,
        onPopBack = viewModel::onPopBack,
        onClickAuth = viewModel::onClickAuth,
        onValueChange = viewModel::onValueChange
    )
}
