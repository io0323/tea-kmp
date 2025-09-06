package com.io.tea.android.ui.search

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.search.state.MyRegionListUseCaseState
import com.io.tea.android.ui.search.state.SearchTeaListViewModelUiState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@SuppressLint("NewApi")
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
    val destination: Destination? by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        // Do not access navigationStateFlow below API 26
        remember { mutableStateOf<Destination?>(null) }
    }
    val useCase by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.useCaseStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.useCaseStateFlow.collectAsState(initial = MyRegionListUseCaseState.Initial)
    }
    val ui by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.uiStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.uiStateFlow.collectAsState(
            initial = SearchTeaListViewModelUiState(
                myRegionList = listOf(),
                isLoad = true,
                isRefresh = false,
                isError = false
            )
        )
    }
    val isSort by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.isSortStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.isSortStateFlow.collectAsState(initial = false)
    }
    val isFilter by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.isFilterStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.isFilterStateFlow.collectAsState(initial = false)
    }
    val isPayDelete by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        viewModel.isPayDeleteStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    } else {
        viewModel.isPayDeleteStateFlow.collectAsState(initial = false)
    }

    destination?.let { dest: Destination? ->
        LaunchedEffect(dest) {
            dest?.let {
                navigator.navigateTo(it)
                viewModel.completeNavigation()
            }
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