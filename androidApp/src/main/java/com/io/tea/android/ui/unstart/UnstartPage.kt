package com.io.tea.android.ui.unstart

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
internal fun NearbyTeaPage(
    regionShopViewModel: UnstartViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(
        viewModelStoreOwner = LocalContext.current as MainActivity,
    ),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by regionShopViewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val navigateTo by mainViewModel.navigateToStateFlow.collectAsStateWithLifecycle(lifecycleOwner)

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            regionShopViewModel.completeNavigation()
        }
    }

    val onClickBottomNavItem = OnClickBottomNavItem {
        navigator.navigateTo(it)
    }

    NearbyStoreScreen(
        onClickBottomNavItem = onClickBottomNavItem,
        onClick = regionShopViewModel::onClick,
        onPopBack = regionShopViewModel::onPopBack,
    )
}