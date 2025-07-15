package com.io.tea.android.ui.payment.confirm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.payment.confirm.model.PointDigits
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun PaymentPointConfirmPage(
    viewModel: PaymentPointConfirmViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    val paymentPointConfirmModel
            by viewModel.paymentPointConfirmModelStateFlow.collectAsStateWithLifecycle(
                lifecycleOwner
            )
    val textStyle = when (paymentPointConfirmModel.pointDigits) {
        PointDigits.WITHIN_DIGITS_5 -> TeaAppTheme.typography.paymentPointConfirm5Digits
        PointDigits.DIGITS_6 -> TeaAppTheme.typography.paymentPointConfirm6Digits
        PointDigits.DIGITS_7 -> TeaAppTheme.typography.paymentPointConfirm7Digits
    }
    val updatePaymentPointConfirmModel = paymentPointConfirmModel.copy(
        pointTextStyle = textStyle,
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    PaymentPointConfirmScreen(
        paymentPointConfirmModel = updatePaymentPointConfirmModel,
        onModifyClick = viewModel::onModifyClick,
        onFixClick = viewModel::onFixClick,
        onPopBack = viewModel::onPopBack,
    )
}
