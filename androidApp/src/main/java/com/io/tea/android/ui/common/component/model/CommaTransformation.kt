package com.io.tea.android.ui.common.component.model

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * 入力された数字に、桁区切りカンマを付与する
 */
class CommaTransformation : VisualTransformation {
    companion object {
        private const val NUMBER_OF_DIGITS = 3 // 3桁ごとにカンマを入れる
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val output = buildString {
            val reversed = text.text.reversed()
            for (index in reversed.indices) {
                if (index > 0 && index % NUMBER_OF_DIGITS == 0) {
                    append(',')
                }
                append(reversed[index])
            }
        }.reversed()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val totalSeparatorCount = (text.length - 1) / NUMBER_OF_DIGITS
                val rightSeparatorCount = (text.length - 1 - offset) / NUMBER_OF_DIGITS
                val leftSeparatorCount = totalSeparatorCount - rightSeparatorCount

                return offset + leftSeparatorCount
            }

            override fun transformedToOriginal(offset: Int): Int {
                val totalSeparatorCount = (text.length - 1) / NUMBER_OF_DIGITS
                val rightSeparatorCount = (output.length - offset) / (NUMBER_OF_DIGITS + 1)
                val leftSeparatorCount = totalSeparatorCount - rightSeparatorCount

                return offset - leftSeparatorCount
            }
        }

        return TransformedText(
            text = AnnotatedString(output),
            offsetMapping = offsetMapping,
        )
    }
}
