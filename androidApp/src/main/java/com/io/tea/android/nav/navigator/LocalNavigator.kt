@file:JvmName("NavigatorKt")

package com.io.tea.android.nav.navigator

import androidx.compose.runtime.compositionLocalOf

val LocalNavigator = compositionLocalOf<Navigator> { error("Navigatorがセットされていない") }
