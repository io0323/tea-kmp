package com.io.tea.android.ui.common.banner.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.placeholderOrigin

@Composable
fun BannerImage(
  painter: AsyncImagePainter,
  contentdescription: String?,
  modifier: Modifier = Modifier,
  alignment: Alignment = Alignment.Center,
  contentScale: ContentScale = ContentScale.Fit,
  alpha: Float = DefaultAlpha,
  colorFilter: ColorFilter? = null,
  errorBackgroundColor: Color = TeaAppTheme.colors.Blue,
) {
  var isError by remember { mutableStateOf(false) }
  val isLoading = if (LocalInspectionMode.current) {
    false
  } else {
    remember(painter.state) {
      when (painter.state) {
        is AsyncImagePainter.State.Empty -> false
        is AsyncImagePainter.State.Loading -> true
        is AsyncImagePainter.State.Error -> {
          isError = true
          false
        }

        is AsyncImagePainter.State.Success -> {
          isError = false
          false
        }
      }
    }
  }

  Image(
    painter = when (LocalInspectionMode.current) {
      true -> painterResource(id = R.drawable.hangryangry)
      false -> painter
    },
    contentDescription = contentdescription,
    modifier = modifier
      .placeholderOrigin(
        visible = isLoading,
        shape = RectangleShape,
      )
      .then(
        when (isError) {
          true -> Modifier.background(errorBackgroundColor)
          false -> Modifier
        },
      ),
    alignment = alignment,
    contentScale = when (LocalInspectionMode.current) {
      true -> ContentScale.Crop
      false -> contentScale
    },
    alpha = alpha,
    colorFilter = colorFilter,
  )
}
