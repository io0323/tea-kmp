package com.io.tea.android.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtil {

    @RequiresApi(Build.VERSION_CODES.O)
    fun toFormat(date: String, pattern: String): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern(pattern)
            val localDate = LocalDate.parse(date, formatter)
            localDate.format(formatter)
        } catch (e: Exception) {
            // TODO : 有効な日付ではなかった場合どうするか？ (Exception)
            date
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toFormatYM(date: String, pattern: String): String {
        return try {
            val ymd = LocalDate.parse(date + "01", DateTimeFormatter.ofPattern("yyyyMMdd"))
            val outputFormatter = DateTimeFormatter.ofPattern(pattern)
            ymd.format(outputFormatter)
        } catch (e: Exception) {
            date
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun toFormatYMD(date: String, pattern: String): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val outputFormatter = DateTimeFormatter.ofPattern(pattern)
            LocalDate.parse(date, formatter).format(outputFormatter)
        } catch (e: Exception) {
            date
        }
    }

    /**
     * 年と月を指定パターンの文字列に変換する
     *
     * @param year 年
     * @param month 月
     * @param pattern 変換パターン（端末の設定言語によって異なる想定）
     * @param locale ロケール
     * @return 変換後の文字列
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun toText(year: Int, month: Int, pattern: String, locale: Locale = Locale.JAPAN): String {
        val formatter = DateTimeFormatter.ofPattern(pattern, locale)
        val date = YearMonth.of(year, month)

        return date.format(formatter)
    }
}
