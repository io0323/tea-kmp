package com.io.tea.android.ui.common.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors

@Composable
fun ChevronRight() {
    Icon(
        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
        tint = Colors.BlueDark,
        contentDescription = "chevron_right",
        modifier = Modifier.size(24.dp)
    )
}