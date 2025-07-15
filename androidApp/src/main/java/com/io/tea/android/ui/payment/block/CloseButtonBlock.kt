package com.io.tea.android.ui.payment.block

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun CloseButtonBlock(onCloseClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = onCloseClick,
            modifier = Modifier
                .padding(top = 12.dp, end = 16.dp)
                .size(24.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "",
                tint = TeaAppTheme.colors.White,
                modifier = Modifier.size(24.dp)

            )
        }
    }
}
