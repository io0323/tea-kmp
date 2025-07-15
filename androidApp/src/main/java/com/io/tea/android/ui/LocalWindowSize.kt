package com.io.tea.android.ui

import androidx.compose.runtime.compositionLocalOf

val LocalWindowSize = compositionLocalOf<WindowSize> { error("Not set WindowSizeClass!") }
