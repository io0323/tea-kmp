package com.io.tea.android.resource

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object Colors {
    // Base Colors
    internal val Blue = Color(0xFF03BAF4) // primary
    internal val BlueLight = Color(0xFF81DCF9) // primary Light
    internal val BlueDark = Color(0xFF02A2D5) // primary blue dark

    // Highlight
    internal val BlueHighlight = Color(0x2603BAF4) // highlight

    //Tonal Scale
    // TODO : White Figma定義外 ※ FigmaではFontを参照していたため切り出し
    internal val White = Color(0xFFFFFFFF)
    internal val White90 = Color(0xE5FFFFFF) // White 90%
    internal val Grey50 = Color(0xFFF4F8F9) // tone50
    internal val Grey100 = Color(0xFFEDF1F2) // tone100
    internal val Grey300 = Color(0xFFDBE2E4) // tone300
    internal val Grey500 = Color(0xFFCCD1D2) // tone500
    internal val Grey600 = Color(0xFF909598) // tone600
    internal val Grey700 = Color(0xFF62686B) // tone700
    internal val Grey800 = Color(0xFF3C3E3F) // tone800
    internal val Grey900 = Color(0xFF272A2A) // tone900
    internal val Black40 = Color(0x99000000) // Black 40%
    internal val Black50 = Color(0x80000000) // Black 50%

    // Font
    internal val FontPrimary = Color(0xFF333333)
    internal val FontSecondary = Color(0xFF717171)
    internal val FontContrast = Color(0xFFFFFFFF)

    // Semantic Colors
    internal val Key = Color(0xFFE20C24)
    internal val Error = Color(0xFFE20C24)

    internal val LabelBackground = Color(0xFFF27087)

    internal val SegmentedButtonBackground = Color(0x2603BAF4)

    internal val TransactionHistoryBackground = Color(0xFF46B733)
    internal val BottomNavTint = Color(0xFF46B733)

    internal val BackgroundGray = Color(0xFFF7F7F7) // TODO : Figma未定義

    // TODO : MyApplicationThemeからの移植
    internal val DarkColors = darkColorScheme(
        primary = Color(0xFFBB86FC),
        secondary = Color(0xFF03DAC5),
        tertiary = Color(0xFF3700B3)
    )

    // TODO : MyApplicationThemeからの移植
    internal val LightColors = lightColorScheme(
        primary = Color(0xFF2196F3),
        secondary = Color(0xFF03DAC5),
        tertiary = Color(0xFF3700B3)
    )

    internal val FontColors = lightColorScheme(
        primary = FontPrimary,
        secondary = FontSecondary,
        surface = FontContrast,
    )


// TODO: カラーコードは以下より抜粋
// TODO: https://www.figma.com/design/pqD97HsW2Sfw5yv7yKeGuJ/Design?node-id=6002-14247&t=NqfYKKjiZKQx3N6n-0

    object Base {
        val primary = Color(0xFF03BAF4)
        val primaryLight = Color(0xFF81DCF9)
        val primaryBlueDark = Color(0xFF02A2D5)
        val highlight = Color(0xFF03BAF4).copy(alpha = 0.15f)

        val tertiary = Color(0xFFCCD1D2)

        val regionListBackground = Color(0xFFEDF1F2)
        val regionListDivider = Color(0xFFDBE2E4)
        val regionListCodeButton = Color(0xFFFFFFFF)
        val regionListCardBackground = Color(0xFFFFFFFF)
        val regionListFilterButton = Color(0xFFFFFFFF)
        val regionListAddedBackground = Color(0x80000000)
        val regionListPrefectureHeader = Color(0xFFDBE2E4)
    }

    object Font {
        val primary = Color(0xFF333333)
        val second = Color(0xFF717171)
        val contrast = Color(0xFFFFFFFF)

        val regionListDeleteEnable = Base.primaryBlueDark
        val regionListDeleteDisable = Base.tertiary
        val regionListAdded = contrast
        val regionListFilterDialogClear = Base.primaryBlueDark
        val regionListFilterDialogFiltering = contrast
    }

    object Semantic {
        val key = Color(0xFFE20C24)
        val error = Color(0xFFE20C24)
    }
}
