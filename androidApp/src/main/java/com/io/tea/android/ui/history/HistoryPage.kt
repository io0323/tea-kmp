package com.io.tea.android.ui.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.bottom.OnClickBottomNavItem
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun TransactionHistoryPage(
    viewModel: HistoryViewModel = koinViewModel(),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val tabList by viewModel.tabListStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val totalPoint by viewModel.totalPointStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val useCase by viewModel.useCaseStateFlow.collectAsStateWithLifecycle(
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