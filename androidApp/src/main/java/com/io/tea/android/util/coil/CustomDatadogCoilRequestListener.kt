package com.io.tea.android.util.coil

import android.net.Uri
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumErrorSource
import okhttp3.HttpUrl
import java.io.File

// DatadogCoilRequestListenerだとCoil2.2.2に対応できてないので暫定的に作成
class CustomDatadogCoilRequestListener : ImageRequest.Listener {
  override fun onError(request: ImageRequest, result: ErrorResult) {
    GlobalRum.get().addError(
      REQUEST_ERROR_MESSAGE,
      RumErrorSource.SOURCE,
      result.throwable,
      extractRequestAttributes(request),
    )
  }

  // endregion

  // region Internals

  private fun extractRequestAttributes(request: ImageRequest): Map<String, Any?> {
    return when (request.data) {
      is String -> {
        mapOf(
          REQUEST_PATH_TAG to request.data as String,
        )
      }
      is Uri -> {
        mapOf(
          REQUEST_PATH_TAG to (request.data as Uri).path,
        )
      }
      is HttpUrl -> {
        mapOf(
          REQUEST_PATH_TAG to (request.data as HttpUrl).toUrl().toString(),
        )
      }
      is File -> {
        mapOf(
          REQUEST_PATH_TAG to (request.data as File).path,
        )
      }
      else -> {
        emptyMap()
      }
    }
  }

  // endregion

  companion object {
    internal const val REQUEST_ERROR_MESSAGE = "Coil request error"
    internal const val REQUEST_PATH_TAG = "request_path"
  }
}
