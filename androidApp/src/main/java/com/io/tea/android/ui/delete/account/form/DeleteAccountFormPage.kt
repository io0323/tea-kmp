package com.io.tea.android.ui.delete.account.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DeleteAccountFormPage(
    noticeId: String,
    viewModel: DeleteAccountFormViewModel = koinViewModel {
        parametersOf(
            noticeId,
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val deleteReason by viewModel.deleteReasonListStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val feedBack by viewModel.feedBackStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val ui by viewModel.uiStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    DeleteAccountFormScreen(
        deleteReason = deleteReason,
        feedBack = feedBack,
        isError = ui.isErrorFeedBackLengthOver,
        isConfirmButtonEnabled = ui.isConfirmButtonEnabled,
    )
}
