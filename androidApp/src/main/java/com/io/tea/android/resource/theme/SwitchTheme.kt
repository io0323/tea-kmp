package com.io.tea.android.resource.theme

import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable

object SwitchTheme {
    val colors: SwitchColors
        @Composable
        get() = SwitchDefaults.colors(
            // NOTE : Switchの色をカスタマイズする際ここで定義する。
        )
}