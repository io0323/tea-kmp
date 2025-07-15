package com.io.tea.android.ui.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.io.tea.android.resource.theme.SwitchTheme
import kotlinx.coroutines.delay

private const val DELAY_MILLIS = 500L

@Composable
fun Switch(
    colors: SwitchColors = SwitchTheme.colors,
    isChecked: Boolean = false,
    isSwitchEnabled: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = {},
) {
    var isEnabled by remember { mutableStateOf(isSwitchEnabled) }
    LaunchedEffect(isChecked) {
        isEnabled = false
        delay(DELAY_MILLIS)
        isEnabled = true
    }

    Box(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min),
    ) {
        Switch(
            colors = colors,
            checked = isChecked,
            onCheckedChange = {
                if (isEnabled) {
                    onCheckedChange?.invoke(it)
                }
            },
            enabled = isEnabled
        )
        if (isEnabled.not()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                    ),
            )
        }
    }
}