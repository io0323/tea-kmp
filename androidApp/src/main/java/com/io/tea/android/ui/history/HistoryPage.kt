package com.io.tea.android.ui.history

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.bottom.OnClickBottomNavItem
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.common.component.model.TabButtonItem
import com.io.tea.android.ui.history.state.TransactionHistoryUiState
import com.io.tea.android.ui.history.state.TransactionHistoryUseCaseState
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun TransactionHistoryPage(
    viewModel: HistoryViewModel = koinViewModel(),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.navigationStateFlow.collectAsState(initial = null)
    }
    val tabList by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.tabListStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.tabListStateFlow.collectAsState(initial = emptyList<TabButtonItem>())
    }
    val totalPoint by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.totalPointStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.totalPointStateFlow.collectAsState(initial = "0")
    }
    val useCase by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.useCaseStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.useCaseStateFlow.collectAsState(initial = TransactionHistoryUseCaseState.Initial)
    }
    val ui by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.uiStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.uiStateFlow.collectAsState(initial = TransactionHistoryUiState(isLoad = false))
    }

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    val onClickBottomNavItem = OnClickBottomNavItem {
        navigator.navigateTo(it)
    }

    TransactionHistoryScreen(
        viewModel = viewModel,
        useCase = useCase,
        tabList = tabList,
        onClickBottomNavItem = onClickBottomNavItem,
        totalPoint = totalPoint,
    )
}