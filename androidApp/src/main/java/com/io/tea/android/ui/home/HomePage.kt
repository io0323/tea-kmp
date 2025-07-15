package com.io.tea.android.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun HomePage(
    viewModel: HomeViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val ui by viewModel.uiStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val display by viewModel.displayStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val useCase by viewModel.useCaseStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val navigateTo by mainViewModel.navigateToStateFlow.collectAsStateWithLifecycle(lifecycleOwner)

    BackHandler(enabled = true) {
        // NOTE: 一旦デバイスのバックボタンを無効にしておく（ログインに戻れないように）
    }

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    HomeScreen(
        viewModel = viewModel,
        displayState = display,
        uiState = ui,
        useCaseState = useCase,
        onClickSetting = viewModel::onClickSetting,
        onClickMyRegion = viewModel::onClickMyRegion,
        onClickBannerItem = viewModel::onClickBannerItem,
        onClickTeaItem = viewModel::onClickTeaItem,
        onClickTeaAppSearch = viewModel::onClickTeaSearch,
        onClickNoticeDetail = viewModel::onClickNoticeDetail,
        onClickNoticeItem = viewModel::onClickNoticeItem,
    )
}
