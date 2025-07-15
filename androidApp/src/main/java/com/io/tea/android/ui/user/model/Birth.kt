package com.io.tea.android.ui.user.model

import java.util.Calendar

data class Birth(
    val year: Int,
    val month: Int
) {
    companion object {
        private val prefix = listOf("")
        private val suffix = listOf("")
        private val yearList: List<Int>
            get() {
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                return (currentYear - 100..currentYear).toList()
            }
        private val monthList: List<Int> = (1..12).toList()
        private const val ELEMENT_COUNT = 2 // 年と月の2つ
        private const val REGEX = "\\d+"

        val default: Birth
            get() {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR) - 100 / 2 // 初期値は50年前（現行仕様）
                val month = calendar.get(Calendar.MONTH) + 1
                return Birth(year, month)
            }

        fun from(text: String): Birth {
            val values = REGEX.toRegex()
                .findAll(text)
                .map { it.value.toIntOrNull() }
                .toList()
                .filterNotNull()

            return if (isValidYearMonth(values)) {
                Birth(values[0], values[1])
            } else {
                default
            }
        }

        private fun isValidYearMonth(values: List<Int>): Boolean {
            val year = Calendar.getInstance().get(Calendar.YEAR)
            return values.size == ELEMENT_COUNT
                    && values[0] in year-100..year
                    && values[1] in 1..12
        }
    }

    fun yearOgions(): List<String> {
        return prefix + yearList.map { it.toString() } + suffix
    }

    fun monthOgions(): List<String> {
        return prefix + monthList.map { it.toString() } + suffix
    }

    fun currentYearIndex(): Int {
        val index = yearList.indexOf(year)
        return if (index < 0) 0 else index
    }

    fun currentMonthIndex(): Int {
        val index = monthList.indexOf(month)
        return if (index < 0) 0 else index
    }
}
