package com.io.tea.android.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.datatransport.BuildConfig
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.menu.block.AppSettingBlock
import com.io.tea.android.ui.menu.block.OtherBlock
import com.io.tea.android.ui.menu.block.TeaAppBlock
import com.io.tea.android.ui.menu.model.PushSettingTypeModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DrawerMenuScreen(
    viewModel: DrawerMenuViewModel = koinViewModel(),
    pushSettingTypeModel: PushSettingTypeModel,
    isLocationSetting: Boolean,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopBar(
                onPopBack = { viewModel.onPopBack() },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                TeaAppBlock(
                    viewModel = viewModel,
                )
                AppSettingBlock(
                    viewModel = viewModel,
                    headerText = stringResource(id = R.string.drawer_menu__app_setting),
                    pushSettingTypeModel = pushSettingTypeModel,
                    isLocationSetting = isLocationSetting,
                )
                OtherBlock(
                    viewModel = viewModel,
                    headerText = stringResource(id = R.string.drawer_menu__others),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp),
                    text = stringResource(
                        R.string.drawer_menu__version,
                        BuildConfig.VERSION_NAME
                    ),
                    style = TeaAppTheme.typography.h4,
                    color = Colors.FontSecondary,
                )
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}


