package com.io.tea.android.ui.menu.block

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.io.tea.android.R
import com.io.tea.android.ui.common.TeaAppHorizontalDivider
import com.io.tea.android.ui.menu.DrawerMenuViewModel
import com.io.tea.android.ui.menu.block.item.SettingListItemRow

@Composable
internal fun TeaAppBlock(
    viewModel: DrawerMenuViewModel
) {
    Column {
        TeaAppHorizontalDivider()

        // Lotを探す
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__region_pay_search),
            onClick = { viewModel.onClickTeaAppSearch() },
        )


        // MyLotを見る
        SettingListItemRow(
            text = stringResource(id = R.string.drawer_menu__my_region_pay),
            onClick = { viewModel.onClickMyTeaApp() },
        )
    }
}
