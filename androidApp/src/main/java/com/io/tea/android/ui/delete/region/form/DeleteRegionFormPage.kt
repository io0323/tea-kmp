package com.io.tea.android.ui.delete.region.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DeleteRegionFormPage(
    noticeId: String,
    viewModel: DeleteRegionFormViewModel = koinViewModel {
        parametersOf(
            noticeId,
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val deletePayList by viewModel.deletePayListStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val deleteReasonList by viewModel.deleteReasonListStateFlow.collectAsStateWithLifecycle(
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

    DeleteRegionFormScreen(
        deletePayList = deletePayList,
        deleteReasonList = deleteReasonList,
        feedBack = feedBack,
        isError = ui.isErrorFeedBackLengthOver,
        isConfirmButtonEnabled = ui.isConfirmButtonEnabled,
    )
}
