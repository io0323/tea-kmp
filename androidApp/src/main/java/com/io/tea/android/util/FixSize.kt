package com.io.tea.android.util

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.TextUnit

internal class FixSize(
    minFontSize: TextUnit,
    maxFontSize: TextUnit,
    px: Int,
    density: Density,
    private val textMeasurer: TextMeasurer,
) {
    private val fontSizeList: List<TextUnit> = generateSequence(minFontSize) { previous ->
        with(density) {
            (previous.toPx() + px).toSp()
        }
    }
        .takeWhile { it < maxFontSize }
        .plus(maxFontSize)
        .toList()

    fun fixSize(
        text: String,
        style: TextStyle,
        constraints: Constraints,
        overflow: TextOverflow,
    ): TextUnit {
        val index = fontSizeList.binarySearch { targetFontSize ->
            val result = textMeasurer.measure(
                text = text,
                style = style.copy(fontSize = targetFontSize),
                constraints = constraints,
                overflow = overflow,
            )
            if (result.hasVisualOverflow) 1 else -1
        }
        assert(index < 0)
        val insertionPoint = -(index + 1)
        val resultIndex = (insertionPoint - 1).coerceAtLeast(0)
        return fontSizeList[resultIndex]
    }
}
