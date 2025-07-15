package com.io.tea.android.util.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

internal interface MultipleEventsCutter {
  fun processEvent(event: () -> Unit)

  companion object
}

internal fun MultipleEventsCutter.Companion.get(): MultipleEventsCutter =
  MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl : MultipleEventsCutter {
  companion object {
    private const val SUPPRESSION_TIME = 300L
  }

  private val now: Long
    get() = System.currentTimeMillis()
  private var lastEventTimeMs: Long = 0

  override fun processEvent(event: () -> Unit) {
    if (now - lastEventTimeMs >= SUPPRESSION_TIME) {
      event.invoke()
    }
    lastEventTimeMs = now
  }
}

@Composable
fun rememberSingleClick(block: () -> Unit): State<() -> Unit> {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  return rememberUpdatedState { multipleEventsCutter.processEvent(block) }
}
