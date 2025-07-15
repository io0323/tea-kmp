package com.io.tea.android.ui.region.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun RegionListPage(
    regionId: String,
    viewModel: RegionListViewModel = koinViewModel {
        parametersOf(
            regionId,
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val model by viewModel.regionListModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val loading by viewModel.loadingState.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    // TODO: 側だけ作成
    RegionListScreen(
        model = model,
        loading = loading,
        onPopBack = viewModel::onPopBack,
        onClickTeaAppDelete = viewModel::onClickTeaAppDelete,
        onChecked = viewModel::onChecked,
        onClickCode = viewModel::onClickCode,
        onCheckedFilter = viewModel::onCheckedFilter,
        onFilterClearClick = viewModel::onFilterClearClick,
        onFilterClick = viewModel::onFilterClick,
        onClickRegionCardItem = viewModel::onClickRegionCardItem,
    )
}