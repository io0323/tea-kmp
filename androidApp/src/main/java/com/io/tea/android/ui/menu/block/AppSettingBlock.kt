package com.io.tea.android.ui.menu.block

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.io.tea.android.R
import com.io.tea.android.ui.menu.block.item.SettingListItemRow
import com.io.tea.android.ui.menu.DrawerMenuViewModel
import com.io.tea.android.ui.menu.block.item.SettingListItemRowWithSwitch
import com.io.tea.android.ui.menu.model.PushSettingTypeModel

@Composable
internal fun AppSettingBlock(
    viewModel: DrawerMenuViewModel,
    headerText: String,
    pushSettingTypeModel: PushSettingTypeModel,
    isLocationSetting: Boolean,
) {
    Column {
        MenuItemHeaderBlock(text = headerText)

        // アカウント情報
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__account),
            onClick = { viewModel.onClickAccount() },
        )

        // セキュリティ
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__security),
            onClick = { viewModel.onClickSecurity() },
        )

        // パスワード設定
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__password_setting),
            onClick = { viewModel.onClickPasswordSetting() },
        )

        // プッシュ通知設定
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__push_setting),
            onClick = {
                viewModel.onClickPushSetting(
                    pushSettingTypeModel.pushSettingType
                )
            },
            subText = stringResource(id = pushSettingTypeModel.subTextRes)
        )

        // 位置情報設定
        SettingListItemRowWithSwitch(
            text = stringResource(id = R.string.drawer_menu__location_setting),
            isChecked = isLocationSetting,
            onCheckedChange = { viewModel.onClickLocationSetting(isLocationSetting) },
        )
    }
}
