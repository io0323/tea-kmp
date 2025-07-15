package com.io.tea.android.ui.home.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonQuaternary
import com.io.tea.android.ui.common.component.model.ButtonIconType
import com.io.tea.android.ui.home.state.HomeDisplayState

@Composable
internal fun HomeSectionHeaderBlock(
    display: HomeDisplayState,
    onClickMyRegion: () -> Unit,
) {
    val isEnabled = display.displayType != HomeDisplayState.DisplayState.INIT
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HomeHeaderLogo()
        ButtonQuaternary(
            isEnabled = isEnabled,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = if (isEnabled) TeaAppTheme.colors.White else TeaAppTheme.colors.White
            ),
            textResId = R.string.home__my_page,
            textStyle = TeaAppTheme.typography.btn,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
            iconType = ButtonIconType.RIGHT,
            onClick = {
                if (isEnabled) {
                    onClickMyRegion()
                }
            },
        )
    }
}

@Composable
fun HomeHeaderLogo() {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = null,
            colorFilter = ColorFilter.tint(TeaAppTheme.colors.White)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(R.string.home__header),
            style = TeaAppTheme.typography.homeTeaApp,
            color = TeaAppTheme.colors.White,
        )
    }
}
