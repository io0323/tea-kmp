package com.io.tea.android.util

object MathUtil {

    fun Int.floorMod(other: Int): Int = when (other) {
        0 -> this
        else -> this - floorDiv(other) * other
    }
}