package com.io.tea.android.resource

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.io.tea.android.R

@Stable
interface AppTypography {
    val h1: TextStyle // h1
    val h2: TextStyle // h2
    val h3: TextStyle // h3
    val h4: TextStyle // h4
    val h5: TextStyle // h5
    val h6: TextStyle // h6
    val body1: TextStyle // TODO : 未定義
    val btn: TextStyle
    val btnL: TextStyle
    val label: TextStyle
    val labelS: TextStyle // TODO : 未定義
    val labelL: TextStyle
    val caption: TextStyle
    val value: TextStyle
    val title1: TextStyle
    val title2: TextStyle
    val subtitle1: TextStyle
    val subtitle2: TextStyle
    val subtitle3: TextStyle
    val poppins: TextStyle
    val footerNav: TextStyle // TODO : 未定義
    val point1: TextStyle
    val point2: TextStyle
    val homeTeaApp: TextStyle
    val regionListTitle: TextStyle
    val regionListDeleteEnable: TextStyle
    val regionListDeleteDisable: TextStyle
    val regionListCodeButtonLabel: TextStyle
    val regionListCountLabel: TextStyle
    val regionListCountUnitLabel: TextStyle
    val regionListCheckboxLabel: TextStyle
    val regionListFilterLabel: TextStyle
    val regionListEmptyLabel: TextStyle
    val regionListCardImageLabel: TextStyle
    val regionListCardPrefecture: TextStyle
    val regionListCardTitle: TextStyle
    val regionListCardPeriodLabel: TextStyle
    val regionListCardPeriod: TextStyle
    val regionListFilterDialogTitle: TextStyle
    val regionListFilterDialogItem: TextStyle
    val regionListFilterDialogClear: TextStyle
    val regionListFilterDialogFiltering: TextStyle
    val paymentPointConfirm5Digits: TextStyle
    val paymentPointConfirm6Digits: TextStyle
    val paymentPointConfirm7Digits: TextStyle
}

@Stable
internal object AppTypographyPhone : AppTypography {

    override val h1: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp,
    )

    override val h2: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 18.sp * 1.48,
    )
    override val h3: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 16.sp * 1.78,
    )
    override val h4: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 15.sp * 1.67,
    )
    override val h5: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp * 1.54,
    )
    override val h6: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp * 1.44,
    )

    // TODO : 未定義
    override val body1: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp * 1.44,
    )
    override val btnL: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 15.sp * 1.75,
    )
    override val btn: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 13.sp * 1.43,
    )
    override val labelL: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 13.sp * 1.57,
    )
    override val label: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 12.sp * 1.28,
    )

    // TODO : 未定義
    override val labelS: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 12.sp * 1.28,
    )
    override val caption: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 12.sp * 1.66,
    )
    override val value: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp * 1.38,
    )
    override val title1: TextStyle = TextStyle(
        fontSize = 70.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 70.sp * 1.5,
    )
    override val title2: TextStyle = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(Font(resId = R.font.poppins_bold)),
        fontWeight = FontWeight.W700,
        lineHeight = 32.sp * 1.34,
    )
    override val subtitle1: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp * 1.34,
    )
    override val subtitle2: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(resId = R.font.poppins_bold)),
        fontWeight = FontWeight.W700,
        lineHeight = 20.sp * 1.48,
    )
    override val subtitle3: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        lineHeight = 14.sp * 1.52,
    )
    override val poppins: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(resId = R.font.poppins_regular)),
        fontWeight = FontWeight.W500,
        lineHeight = 14.sp * 1.52,
    )

    // TODO : 未定義
    override val footerNav: TextStyle = TextStyle(
        fontSize = 8.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        lineHeight = 8.sp * 1.5,
    )
    override val point1: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(resId = R.font.notosansjp_regular)),
        fontWeight = FontWeight.W500,
        lineHeight = 24.sp,
    )
    override val point2: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(resId = R.font.notosansjp_regular)),
        fontWeight = FontWeight.W400,
        lineHeight = 12.sp,
    )
    override val homeTeaApp: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        lineHeight = 20.sp * 1.5,
    )
    override val regionListTitle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
    )
    override val regionListDeleteEnable: TextStyle = TextStyle(
        color = Colors.Font.regionListDeleteEnable,
        fontSize = 13.sp,
        fontWeight = FontWeight.W400,
    )
    override val regionListDeleteDisable: TextStyle = TextStyle(
        color = Colors.Font.regionListDeleteDisable,
        fontSize = 13.sp,
        fontWeight = FontWeight.W400,
    )
    override val regionListCodeButtonLabel: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.W700,
    )
    override val regionListCountLabel: TextStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.W700,
    )
    override val regionListCountUnitLabel: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
    )
    override val regionListCheckboxLabel: TextStyle = TextStyle(
        color = Colors.Font.primary,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
    )
    override val regionListFilterLabel: TextStyle = TextStyle(
        color = Colors.Font.primary,
        fontSize = 13.sp,
        fontWeight = FontWeight.W400,
    )
    override val regionListEmptyLabel: TextStyle = TextStyle(
        color = Colors.Font.second,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
    )
    override val regionListCardImageLabel: TextStyle = TextStyle(
        color = Colors.Font.regionListAdded,
        fontSize = 15.sp,
        fontWeight = FontWeight.W700,
    )
    override val regionListCardPrefecture: TextStyle = TextStyle(
        color = Colors.Font.primary,
        fontSize = 15.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 25.sp,
    )
    override val regionListCardTitle: TextStyle = TextStyle(
        color = Colors.Font.primary,
        fontSize = 15.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 25.sp,
    )
    override val regionListCardPeriodLabel: TextStyle = TextStyle(
        color = Colors.Font.second,
        fontSize = 13.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 18.sp,
    )
    override val regionListCardPeriod: TextStyle = TextStyle(
        color = Colors.Font.primary,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
    )
    override val regionListFilterDialogTitle: TextStyle = TextStyle(
        color = Colors.Font.primary,
        fontSize = 16.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 28.sp
    )
    override val regionListFilterDialogItem: TextStyle = TextStyle(
        color = Colors.Font.primary,
        fontSize = 13.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 20.sp
    )
    override val regionListFilterDialogClear: TextStyle = TextStyle(
        color = Colors.Font.regionListFilterDialogClear,
        fontSize = 15.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 26.sp
    )
    override val regionListFilterDialogFiltering: TextStyle = TextStyle(
        color = Colors.Font.regionListFilterDialogFiltering,
        fontSize = 15.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 26.sp
    )
    override val paymentPointConfirm5Digits: TextStyle = TextStyle(
        fontSize = 70.sp,
        fontFamily = FontFamily(Font(resId = R.font.poppins_medium)),
        fontWeight = FontWeight.W500,
        lineHeight = 70.sp * 1.5
    )
    override val paymentPointConfirm6Digits: TextStyle = TextStyle(
        fontSize = 61.sp,
        fontFamily = FontFamily(Font(resId = R.font.poppins_medium)),
        fontWeight = FontWeight.W500,
        lineHeight = 61.sp * 1.5
    )
    override val paymentPointConfirm7Digits: TextStyle = TextStyle(
        fontSize = 50.sp,
        fontFamily = FontFamily(Font(resId = R.font.poppins_medium)),
        fontWeight = FontWeight.W500,
        lineHeight = 50.sp * 1.5
    )
}