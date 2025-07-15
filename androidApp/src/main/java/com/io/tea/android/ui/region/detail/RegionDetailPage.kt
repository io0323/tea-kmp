package com.io.tea.android.ui.region.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun RegionDetailPage(
    regionId: String,
    viewModel: RegionDetailViewModel = koinViewModel {
        parametersOf(
            regionId,
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val useCase by viewModel.useCaseStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val agree by viewModel.agreeStateFlow.collectAsStateWithLifecycle(
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
    RegionDetailScreen(
        viewModel = viewModel,
        useCase = useCase,
        agree = agree,
        isConfirmButtonEnabled = ui.isConfirmButtonEnabled,
    )
}
