package com.io.tea.android.ui.security

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SecurityPage(
    viewModel: SecurityViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val isSecurityPassCodeLockEnabled by viewModel.isSecurityPassCodeLockEnabled.collectAsState()

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }
    SecurityScreen(
        viewModel = viewModel,
        isSecurityPassCodeLockEnabled
    )
}