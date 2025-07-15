package com.io.tea.android.util

object StringUtil {

    fun formatComma(number: Long): String {
        return "%,d".format(number)
    }

    /**
     * strが数値文字列かつdigit(桁数)かを判定します
     */
    fun isCntNumMatched(str: String, digit: Int): Boolean {
        return str.matches(Regex("\\d{$digit}"))
    }

    fun String.toIntSafe(): Int {
        return this.toIntOrNull() ?: 0
    }
}