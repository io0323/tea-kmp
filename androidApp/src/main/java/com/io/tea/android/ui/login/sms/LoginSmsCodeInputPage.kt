package com.io.tea.android.ui.login.sms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.common.state.SmsSource
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun LogInSmsCodeInputPage(
    source: SmsSource,
    viewModel: LogInSmsCodeInputViewModel = koinViewModel {
        parametersOf(source)
    },
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val loginSmsState by viewModel.loginSmsStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val model by viewModel.loginSmsCodeInputModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    LogInSmsCodeInputScreen(
        model = model,
        loginSmsState = loginSmsState,
        onPopBack = viewModel::onPopBack,
        onClickAuth = viewModel::onClickAuth,
        onValueChange = viewModel::onValueChange
    )
}