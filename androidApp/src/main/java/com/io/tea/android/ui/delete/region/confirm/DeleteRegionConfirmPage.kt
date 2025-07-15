package com.io.tea.android.ui.delete.region.confirm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DeleteRegionConfirmPage(
    noticeId: String,
    viewModel: DeleteRegionConfirmViewModel = koinViewModel {
        parametersOf(
            noticeId,
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val deletePay by viewModel.deletePayStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val deleteReasonList by viewModel.deleteReasonListStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val feedBack by viewModel.feedBackStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val cautionList by viewModel.cautionListStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val isChecked by viewModel.isCheckedStateFlow.collectAsStateWithLifecycle(
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

    DeleteRegionConfirmScreen(
        deletePay = deletePay,
        deleteReasonList = deleteReasonList,
        feedBack = feedBack,
        cautionList = cautionList,
        isChecked = isChecked,
        isConfirmButtonEnabled = ui.isConfirmButtonEnabled,
    )
}
