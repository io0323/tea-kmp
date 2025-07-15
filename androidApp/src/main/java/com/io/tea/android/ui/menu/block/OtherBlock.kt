package com.io.tea.android.ui.menu.block

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.io.tea.android.R
import com.io.tea.android.ui.menu.DrawerMenuViewModel
import com.io.tea.android.ui.menu.block.item.SettingListItemRow

@Composable
internal fun OtherBlock(
    viewModel: DrawerMenuViewModel,
    headerText: String,
) {
    Column {
        MenuItemHeaderBlock(text = headerText)

        // 使い方
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__usage),
            onClick = { viewModel.onClickUsage() },
        )

        // よくある質問
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__faq),
            onClick = { viewModel.onClickFaq() },
        )

        // region PAYとは
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__what),
            onClick = { viewModel.onClickWhat() },
        )

        // 利用規約
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__tos),
            onClick = { viewModel.onClickTos() },
        )

        // プライバシーポリシー
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__privacy_policy),
            onClick = { viewModel.onClickPrivacyPolicy() },
        )

        // 商標・ライセンス
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__license),
            onClick = { viewModel.onClickLicense() },
        )

        // 退会
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__unsubscribe),
            onClick = { viewModel.onClickUnsubscribe() },
        )

        // ログアウト
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__logout),
            onClick = { viewModel.onClickLogout() },
        )
    }
}
