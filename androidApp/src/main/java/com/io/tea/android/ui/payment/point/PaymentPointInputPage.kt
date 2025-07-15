package com.io.tea.android.ui.payment.point

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
internal fun PaymentPointInputPage(
    viewModel: PaymentPointInputViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val paymentPointInputModelStateFlow
            by viewModel.paymentPointInputModelStateFlow.collectAsStateWithLifecycle(lifecycleOwner)

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    PaymentPointInputScreen(
        pointInputModel = paymentPointInputModelStateFlow,
        onCloseClick = viewModel::onCloseClick,
        onValueChange = viewModel::onInputPointChanged,
        onClickNext = viewModel::onClickNext,
    )
}

