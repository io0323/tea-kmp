package com.io.tea.android.resource.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.io.tea.android.resource.AppTypography
import com.io.tea.android.resource.AppTypographyPhone
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.Colors.DarkColors
import com.io.tea.android.resource.Colors.LightColors
import com.io.tea.android.resource.shapes

object TeaAppTheme {
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current

    val colors: Colors
        @Composable
        get() = LocalAppColors.current
}

internal val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("AppTypographyが設定されていません")
}
internal val LocalAppColors = staticCompositionLocalOf<Colors> {
    error("Colorsが設定されていません")
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeaAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAppTypography provides AppTypographyPhone,
        LocalAppColors provides Colors,
        LocalOverscrollConfiguration provides null,
    ) {
        MaterialTheme(
            colorScheme = if (isDarkTheme) DarkColors else LightColors,
            typography = MaterialTheme.typography,
            shapes = shapes,
        ) {
            CompositionLocalProvider(LocalContentColor provides LocalContentColor.current.copy(alpha = 1f)) {
                content()
            }
        }
    }
}