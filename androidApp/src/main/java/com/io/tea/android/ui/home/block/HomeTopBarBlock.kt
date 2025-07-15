package com.io.tea.android.ui.home.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBarBlock(
    onClickSetting: () -> Unit,
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            HomeTopBarLogo()
        },
        navigationIcon = {},
        actions = {
            IconButton(
                onClick = { onClickSetting() },
            ) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    )
}

@Composable
fun HomeTopBarLogo() {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = null,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = null,
            modifier = Modifier
        )
    }
}
