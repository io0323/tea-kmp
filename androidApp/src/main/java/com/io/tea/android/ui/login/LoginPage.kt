package com.io.tea.android.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun LogInPage(
    viewModel: LogInViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val model by viewModel.loginModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }
    LogInScreen(
        model = model,
        onPopBack = viewModel::onPopBack,
        onClickForm = viewModel::onClickForm,
        onClickPicker = viewModel::onClickPicker,
        onClickLogIn = viewModel::onClickLogIn,
        onPhoneNumberChanged = viewModel::onPhoneNumberChanged,
        onValueChangeMailAddress = viewModel::onValueChangeMailAddress,
        onValueChangePassword = viewModel::onValueChangePassword,
    )
}
