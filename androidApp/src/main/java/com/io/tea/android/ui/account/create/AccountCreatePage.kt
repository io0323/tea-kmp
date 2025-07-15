package com.io.tea.android.ui.account.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AccountCreatePage(
    viewModel: AccountCreateViewModel = koinViewModel(),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val accountCreateModel by viewModel.accountCreateModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    AccountCreateScreen(
        model = accountCreateModel,
        onPopBack = viewModel::onPopBack,
        onClickSendSms = viewModel::onClickSendSms,
        onClickPicker = viewModel::onClickPicker,
        onPhoneNumberChanged = viewModel::onPhoneNumberChanged,
        onValueChangePassword = viewModel::onValueChangePassword,
        onLinkClick = viewModel::onLinkClick,
        onCheckedChangeTerms = viewModel::onCheckedChangeTerms,
        onCheckedChangePrivacy = viewModel::onCheckedChangePrivacy,
    )
}
