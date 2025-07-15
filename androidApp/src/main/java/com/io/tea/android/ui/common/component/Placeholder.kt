package com.io.tea.android.ui.common.component

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.io.tea.android.ui.LocalWindowSize

private const val ROUNDED_CORNER_SHAPE = 100

@Composable
fun Modifier.placeholderOrigin(
  visible: Boolean = true,
  color: Color = Color.Unspecified,
  shape: Shape? = RoundedCornerShape(100.dp),
  highlight: PlaceholderHighlight? = PlaceholderHighlight.fade(),
  placeholderFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
  contentFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
) = composed() {
  Modifier.placeholder(
    visible = visible,
    color = color,
    shape = shape,
    highlight = highlight,
    placeholderFadeTransitionSpec = placeholderFadeTransitionSpec,
    contentFadeTransitionSpec = contentFadeTransitionSpec,
  )
}

@Composable
fun SkeletonText(
  text: String = "",
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  fontSize: TextUnit = TextUnit.Unspecified,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  lineHeight: TextUnit = TextUnit.Unspecified,
  overflow: TextOverflow = TextOverflow.Ellipsis,
  softWrap: Boolean = true,
  maxLines: Int = 1,
  onTextLayout: (TextLayoutResult) -> Unit = {},
  style: TextStyle = LocalTextStyle.current,
) {
  Text(
    text = text,
    modifier = modifier.placeholderOrigin(),
    color = color,
    fontSize = fontSize,
    fontStyle = fontStyle,
    fontWeight = fontWeight,
    fontFamily = fontFamily,
    letterSpacing = letterSpacing,
    textDecoration = textDecoration,
    textAlign = textAlign,
    lineHeight = lineHeight,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    onTextLayout = onTextLayout,
    style = style,
  )
}

@Composable
fun SkeletonCircleButton(times: Int) {
  Column {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement =
      if (LocalWindowSize.current.isTablet) {
        Arrangement.End
      } else {
        Arrangement.Start
      },
    ) {
      Repeat(
        times = times,
        between = {
          Box(
            modifier = Modifier
              .placeholderOrigin(shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE))
              .size(32.dp),
          )
        },
        content = {
          if (it > 0) {
            Spacer(modifier = Modifier.width(8.dp))
          }
        },
      )
    }
  }
}

@Composable
private fun Repeat(
  times: Int,
  between: @Composable () -> Unit,
  content: @Composable (Int) -> Unit,
) {
  for (index in 0 until times) {
    content(index)
    if (index < times - 1) {
      between()
    }
  }
}
