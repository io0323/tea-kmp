package com.io.tea.android.ui.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.home.state.HomeDisplayState
import com.io.tea.android.ui.home.state.HomeUiState
import com.io.tea.android.ui.home.state.HomeUseCaseState
import org.koin.androidx.compose.koinViewModel

@SuppressLint("NewApi")
@Composable
internal fun HomePage(
    viewModel: HomeViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val destination: Destination? by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        // Do not access navigationStateFlow below API 26
        mutableStateOf<Destination?>(null)
    }
    val ui by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.uiStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.uiStateFlow.collectAsState(initial = HomeUiState())
    }
    val display by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.displayStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.displayStateFlow.collectAsState(initial = HomeDisplayState())
    }
    val useCase by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.useCaseStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.useCaseStateFlow.collectAsState(initial = HomeUseCaseState.Initial)
    }
    val navigateTo by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        mainViewModel.navigateToStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        mainViewModel.navigateToStateFlow.collectAsState(initial = null)
    }

    BackHandler(enabled = true) {
        // NOTE: 一旦デバイスのバックボタンを無効にしておく（ログインに戻れないように）
    }

    destination?.let { dest: Destination ->
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
