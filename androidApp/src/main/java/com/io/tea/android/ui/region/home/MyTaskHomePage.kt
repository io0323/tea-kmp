package com.io.tea.android.ui.region.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.MainActivity
import com.io.tea.android.nav.bottom.OnClickBottomNavItem
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun RegionHomePage(
    regionHomeViewModel: MyTeaHomeViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by regionHomeViewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val navigateTo by mainViewModel.navigateToStateFlow.collectAsStateWithLifecycle(lifecycleOwner)

    val regionHomeModel by regionHomeViewModel.regionHomeModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner = lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            regionHomeViewModel.completeNavigation()
        }
    }

    val onClickBottomNavItem = OnClickBottomNavItem {
        navigator.navigateTo(it)
    }

    RegionHomeScreen(
        regionHomeModel = regionHomeModel,
        onPopBack = regionHomeViewModel::onPopBack,
        onPeriodDetailClick = regionHomeViewModel::onPeriodDetailClick,
        onPointClick = regionHomeViewModel::onPointClick,
        onChargeClick = regionHomeViewModel::onChargeClick,
        onPaymentClick = regionHomeViewModel::onPaymentClick,
        onBusinessNumberClick = regionHomeViewModel::onBusinessNumberClick,
        onCampaignClick = regionHomeViewModel::onCampaignClick,
        onBannerClick = regionHomeViewModel::onBannerClick,
        onNoticeDetailClick = regionHomeViewModel::onNoticeDetailClick,
        onNoticeItemClick = regionHomeViewModel::onNoticeItemClick,
        onClickBottomNavItem = onClickBottomNavItem
    )
}