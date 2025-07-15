package com.io.tea.android.ui.delete.account.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.R
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.common.state.SmsSource
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DeleteAccountAuthPage(
    source: SmsSource,
    viewModel: DeleteAccountAuthViewModel = koinViewModel {
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
    val deleteAccountAuth by viewModel.deleteAccountAuthsStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val countryCode by viewModel.countryCodeStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }
    DeleteAccountAuthScreen(
        countryCode = countryCode,
        deleteAccountAuth = deleteAccountAuth,
        errorList = listOf(
            R.string.delete_account_auth___error_char_more,
            R.string.delete_account_auth___error_char_type,
            R.string.delete_account_auth___error_mail,
        ),
        onPopBack = { viewModel.onPopBack() },
        onClickCancel = { viewModel.onClickCancel() },
        onClickLogin = { viewModel.onClickLogin() },
    )
}