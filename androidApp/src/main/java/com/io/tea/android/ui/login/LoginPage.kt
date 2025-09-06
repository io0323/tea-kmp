package com.io.tea.android.ui.login

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.login.model.LogInModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("NewApi")
@Composable
internal fun LogInPage(
    viewModel: LogInViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination: Destination? by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        // Do not access navigationStateFlow below API 26
        remember { mutableStateOf<Destination?>(null) }
    }
    val model by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.loginModelStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.loginModelStateFlow.collectAsState(initial = LogInModel.default)
    }

    destination?.let { dest: Destination? ->
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
