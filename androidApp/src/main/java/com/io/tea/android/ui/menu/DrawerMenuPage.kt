package com.io.tea.android.ui.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.io.tea.android.R
import com.io.tea.android.nav.navigator.LocalNavigator
import com.io.tea.android.ui.dialog.ShowAlertDialog
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun DrawerMenuPage(
    noticeId: String,
    viewModel: DrawerMenuViewModel = koinViewModel {
        parametersOf(
            noticeId,
        )
    },
) {
    val navigator = LocalNavigator.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
    val pushSettingTypeModel by viewModel.pushSettingTypeModelStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val isLocationSetting by viewModel.isLocationSettingStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )
    val isLogout by viewModel.isLogoutStateFlow.collectAsStateWithLifecycle(
        lifecycleOwner
    )

    destination?.let { dest ->
        LaunchedEffect(dest) {
            navigator.navigateTo(dest)
            viewModel.completeNavigation()
        }
    }

    if (isLogout) {

        ShowAlertDialog(
            title = stringResource(R.string.logout__confirm_dialog_title),
            text = stringResource(R.string.logout__confirm_dialog_text),
            confirmButtonText = R.string.common__cancel,
            dismissButtonText = R.string.common__ok,
            cancelOnDismiss = true,
            onConfirmClick = {},
            onDismissClick = {},
        )
//        ConformAlertDialog()
    }

    DrawerMenuScreen(
        pushSettingTypeModel = pushSettingTypeModel,
        isLocationSetting = isLocationSetting,
    )
}
