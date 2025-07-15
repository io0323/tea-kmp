package com.io.tea.android.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.io.tea.android.resource.Colors

object ColorUtil {

    fun hexToColor(hex: String): Color {
        return try {
            Color(hexToColorInt(hex))
        } catch (e: Exception) {
            // TODO カラーコードでない場合
            Colors.Grey900
        }
    }

    /**
     * 2つの色を重ねた色を取得する
     *
     * @param source 元の色
     * @param plus 重ねる色
     * @param alpha 重ねる色の透明度
     * @return 2つの色を重ねた色
     */
    fun compositeOver(source: Color, plus: Color, alpha: Float = 1.0f): Color {
        val plusColor = plus.copy(alpha = alpha)
        return plusColor.compositeOver(source)
    }

    private fun hexToColorInt(hex: String): Int {
        return android.graphics.Color.parseColor(hex)
    }
}