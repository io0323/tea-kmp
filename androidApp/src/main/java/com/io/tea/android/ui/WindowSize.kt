package com.io.tea.android.ui

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.window.layout.WindowMetricsCalculator

const val TABLET_THRESHOLD_WIDTH = 600

/**
 * https://material.io/archive/guidelines/layout/responsive-ui.html
 * ↑をプロジェクトでの使用方法に合うように修正
 */
enum class WindowSize(val type: Type, val orientation: Orientation) {
  PhonePortrait(Type.Phone, Orientation.Portrait),
  PhoneLandscape(Type.Phone, Orientation.Landscape),
  TabletPortrait(Type.Tablet, Orientation.Portrait),
  TabletLandscape(Type.Tablet, Orientation.Landscape),
  ;

  enum class Type { Phone, Tablet }
  enum class Orientation { Portrait, Landscape }

  val isPhone get() = type == Type.Phone
  val isTablet get() = type == Type.Tablet
  val isPortrait get() = orientation == Orientation.Portrait
  val isLandscape get() = orientation == Orientation.Landscape
}

/**
 * Remembers the [WindowSize] class for the window corresponding to the current window metrics.
 */
@Composable
fun Activity.rememberWindowSizeClass(): WindowSize {
  // Get the size (in pixels) of the window
  val windowSize = rememberWindowSize()

  // Convert the window size to [Dp]
  val windowDpSize = with(LocalDensity.current) {
    windowSize.toDpSize()
  }

  // Calculate the window size class
  return getWindowSizeClass(windowDpSize, LocalConfiguration.current)
}

fun Activity.windowSizeClass(config: Configuration): WindowSize {
  val windowSize = windowSize()
  val windowDpSize = with(Density(this)) {
    windowSize.toDpSize()
  }
  return getWindowSizeClass(windowDpSize, config)
}

/**
 * Remembers the [Size] in pixels of the window corresponding to the current window metrics.
 */
@Composable
private fun Activity.rememberWindowSize(): Size {
  val configuration = LocalConfiguration.current
  // WindowMetricsCalculator implicitly depends on the configuration through the activity,
  // so re-calculate it upon changes.
  val windowMetrics = remember(configuration, configuration.orientation) {
    WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
  }
  return windowMetrics.bounds.toComposeRect().size
}

private fun Activity.windowSize(): Size {
  val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
  return windowMetrics.bounds.toComposeRect().size
}

/**
 * Partitions a [DpSize] into a enumerated [WindowSize] class.
 */
internal fun getWindowSizeClass(windowDpSize: DpSize, config: Configuration): WindowSize {
  val isPhone = isTabletWidth(config).not()
  val isPortrait = windowDpSize.width < windowDpSize.height
  return when {
    (isPhone) && (isPortrait) -> WindowSize.PhonePortrait
    (isPhone) && (!isPortrait) -> WindowSize.PhoneLandscape
    (!isPhone) && (isPortrait) -> WindowSize.TabletPortrait
    (!isPhone) && (!isPortrait) -> WindowSize.TabletLandscape
    else -> throw IllegalStateException("unreachable")
  }
}

private fun isTabletWidth(config: Configuration) = isTabletWidth(config.smallestScreenWidthDp)
private fun isTabletWidth(smallestScreenWidth: Int): Boolean {
  return smallestScreenWidth >= TABLET_THRESHOLD_WIDTH
}

fun Activity.isTablet(): Boolean {
  return windowSizeClass(resources.configuration).isTablet
}

fun Activity.isPhone(): Boolean {
  return isTablet().not()
}
