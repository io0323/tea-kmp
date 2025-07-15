package com.io.tea.android.ui.codeinput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.nav.navigator.LocalNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CodeInputPage(
    viewModel: CodeInputViewModel = koinViewModel(),
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val model by viewModel.codeInputModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }
    CodeInputScreen(
        model = model,
        onPopBack = viewModel::onPopBack,
        onValueChange = viewModel::onValueChange,
        onClickButtonAdd = viewModel::onClickButtonAdd,
    )
}