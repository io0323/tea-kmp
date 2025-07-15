package com.io.tea.android.ui.notice.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun NoticeDetailPage(
    noticeId: String,
    viewModel: NoticeDetailViewModel = koinViewModel {
        parametersOf(
            noticeId,
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val ui by viewModel.uiStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val noticeDetail by viewModel.noticeDetailStateFlow.collectAsStateWithLifecycle(lifecycleOwner)


    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    NoticeDetailScreen(
        noticeDetail = noticeDetail,
        isLoad = ui.isLoad
    )
}
