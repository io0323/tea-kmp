package com.io.tea.android.ui.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun MyRegionListPage(
    regionId: String,
    viewModel: SearchTeaListViewModel = koinViewModel {
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
    val ui by viewModel.uiStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val isSort by viewModel.isSortStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val isFilter by viewModel.isFilterStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val isPayDelete by viewModel.isPayDeleteStateFlow.collectAsStateWithLifecycle(lifecycleOwner)

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    MyRegionListScreen(
        viewModel = viewModel,
        state = useCase,
        isSort = isSort,
        isFilter = isFilter,
        isPayDelete = isPayDelete,
    )
}