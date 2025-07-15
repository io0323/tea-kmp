package com.io.tea.android.ui.delete.account.complete

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
internal fun DeleteAccountCompletePage(
    viewModel: DeleteAccountCompleteViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }
    DeleteAccountCompleteScreen(viewModel = viewModel)
}