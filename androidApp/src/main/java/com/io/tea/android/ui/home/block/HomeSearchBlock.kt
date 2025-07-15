package com.io.tea.android.ui.home.block

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonPrimary
import com.io.tea.android.ui.common.component.model.ButtonIconType

@Composable
fun HomeSearchBlock(
    onClickSearch: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        ButtonPrimary(
            icon = android.R.drawable.ic_menu_close_clear_cancel,
            iconType = ButtonIconType.LEFT,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 5.5.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp),
            buttonModifier = Modifier
                .weight(1f)
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                ),
            textResId = R.string.home__my_page_search,
            textStyle = TeaAppTheme.typography.btnL,
            textResColor = TeaAppTheme.colors.White,
            tintRes = TeaAppTheme.colors.White,
            isEnabled = true,
            onClick = { onClickSearch() },
        )
    }
}
