package com.io.tea.android.nav

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.io.tea.android.nav.bottom.MainBottomNavViewModel
import com.io.tea.android.nav.graph.MainNavGraph
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.nav.navigator.NavigatorFactory
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.LocalWindowSize
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.WindowSize

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun AppCompositionLocal(
    windowSize: WindowSize,
    mainViewModel: MainViewModel,
    bottomNavViewModel: MainBottomNavViewModel,
) {
    val navController = rememberNavController()
    val navigator by remember(navController) {
        derivedStateOf {
            NavigatorFactory.create(navController)
        }
    }
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    val navigateTo: @Composable () -> Unit = {
        val navigateToValue by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mainViewModel.navigateToStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
        } else {
            // Safe fallback for API < 26 - use null as initial value
            remember { mutableStateOf<Destination?>(null) }
        }
        navigateToValue?.let { dest ->
            LaunchedEffect(dest) {
                navigator.navigateTo(dest)
                mainViewModel.completeToNavigation()
            }
        }
    }
    val startDestination by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        mainViewModel.startDestinationRoute.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        // Safe fallback for API < 26 - use null as initial value
        remember { mutableStateOf<String?>(null) }
    }

    CompositionLocalProvider(
        LocalWindowSize provides windowSize,
        LocalNavigator provides navigator,
    ) {
        TeaAppTheme() {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                startDestination?.let {
                    MainNavGraph(navController, it)
                    navigateTo()
                }
            }
        }
    }
}