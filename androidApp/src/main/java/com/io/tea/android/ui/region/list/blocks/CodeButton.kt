package com.io.tea.android.ui.region.list.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.ui.common.component.ButtonSecondary

@Composable
fun CodeButton(
    onClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.Base.regionListCodeButton)
            .padding(horizontal = 40.dp, vertical = 20.dp)
            .height(40.dp)
    ) {
        ButtonSecondary(
            isEnabled = true,
            textResId = R.string.region_list__code_button_title,
            buttonModifier = Modifier.weight(1f),
            onClick = onClick,
        )
    }
}
