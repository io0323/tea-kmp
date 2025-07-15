package com.io.tea.android.util

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.io.tea.android.resource.theme.TeaAppTheme

object ButtonUtil {
    @SuppressLint("ModifierParameter")
    @Composable
    fun FixText(
        text: String,
        textStyle: TextStyle,
        textAlign: TextAlign = TextAlign.Center,
        color: Color,
        minFontSize: TextUnit = TeaAppTheme.typography.caption.fontSize,
        maxLines: Int = 1,
        modifier: Modifier = Modifier,
        px: Int = 1, // 粒度
    ) {
        val maxFontSize = textStyle.fontSize
        val fixSize = rememberFixSize(minFontSize, maxFontSize, px)
        val style = LocalTextStyle.current

        BoxWithConstraints {
            val fontSize = remember(text, fixSize, textStyle, constraints) {
                fixSize.fixSize(
                    text = text,
                    style = style,
                    constraints = constraints,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                text = text,
                fontSize = fontSize,
                overflow = TextOverflow.Ellipsis,
                style = textStyle,
                color = color,
                textAlign = textAlign,
                maxLines = maxLines,
                modifier = modifier,
            )
        }
    }

    @Composable
    internal fun rememberFixSize(
        minFontSize: TextUnit,
        maxFontSize: TextUnit,
        px: Int,
    ): FixSize {
        val density = LocalDensity.current
        val textMeasurer = rememberTextMeasurer()
        return remember(minFontSize, maxFontSize, px, density, textMeasurer) {
            FixSize(minFontSize, maxFontSize, px, density, textMeasurer)
        }
    }
}