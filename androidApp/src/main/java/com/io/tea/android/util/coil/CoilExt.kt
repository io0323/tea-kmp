package com.io.tea.android.util.coil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

/**
 * 独自カスタムの [coil.compose.rememberImagePainter]
 */
@Composable
inline fun rememberCustomImagePainter(
  data: Any?,
  size: Size, // iconなどのresource系はSize.ORIGINALを、urlから取得するのは用途にあったSizeを指定する
  builder: ImageRequest.Builder.() -> Unit = {},
): AsyncImagePainter {
  val imageRequest = ImageRequest
    .Builder(LocalContext.current)
    .data(data)
    // XXX: sizeを設定していないと、Android9でpainter.stateがLoadingから進まない
    // https://stackoverflow.com/questions/74009228/coil-rememberasyncimagepainter-states-are-not-updated
    .size(size)
    .listener(CustomDatadogCoilRequestListener())
    .apply(builder)
    .build()

  val rememberImageRequest by remember(data, size) {
    mutableStateOf(imageRequest)
  }
  return rememberAsyncImagePainter(
    model = rememberImageRequest,
  )
}

/**
 * 独自カスタムの [coil.compose.rememberImagePainter]
 */
@Composable
inline fun rememberCustomImagePainter(
  data: Any?,
  size: Size, // iconなどのresource系はSize.ORIGINALを、urlから取得するのは用途にあったSizeを指定する
  imageLoader: ImageLoader,
  builder: ImageRequest.Builder.() -> Unit = {},
): AsyncImagePainter {
  val imageRequest = ImageRequest
    .Builder(LocalContext.current)
    .data(data)
    .size(size)
    .listener(CustomDatadogCoilRequestListener())
    .apply(builder)
    .build()

  val rememberImageRequest by remember(data, size) {
    mutableStateOf(imageRequest)
  }

  return rememberAsyncImagePainter(
    model = rememberImageRequest,
    imageLoader = imageLoader,
  )
}
