package com.io.tea.android.util.event

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.ContentAlpha
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Tab
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay

fun Modifier.clickableSingle(
  interactionSource: MutableInteractionSource? = null,
  indication: Indication? = null,
  enabled: Boolean = true,
  onClickLabel: String? = null,
  role: Role? = null,
  onClick: () -> Unit,
) = composed(
  inspectorInfo = debugInspectorInfo {
    name = "clickable"
    properties["interactionSource"] = interactionSource
    properties["indication"] = indication
    properties["enabled"] = enabled
    properties["onClickLabel"] = onClickLabel
    properties["onClick"] = onClick
    properties["role"] = role
  },
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  Modifier.clickable(
    interactionSource = interactionSource ?: remember { MutableInteractionSource() },
    indication = indication,
    enabled = enabled,
    onClickLabel = onClickLabel,
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    role = role,
  )
}

fun Modifier.clickableSingle(
  enabled: Boolean = true,
  onClickLabel: String? = null,
  role: Role? = null,
  onClick: () -> Unit,
) = composed(
  inspectorInfo = debugInspectorInfo {
    name = "clickable"
    properties["enabled"] = enabled
    properties["onClickLabel"] = onClickLabel
    properties["role"] = role
    properties["onClick"] = onClick
  },
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  Modifier.clickable(
    enabled = enabled,
    onClickLabel = onClickLabel,
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    role = role,
    indication = LocalIndication.current,
    interactionSource = remember { MutableInteractionSource() },
  )
}

@Composable
fun SingleClickButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(),
  shape: Shape = MaterialTheme.shapes.small,
  border: BorderStroke? = null,
  colors: ButtonColors = ButtonDefaults.buttonColors(),
  contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
  content: @Composable RowScope.() -> Unit,
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  Button(
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    colors = colors,
    contentPadding = contentPadding,
    content = content,
  )
}

@Composable
fun SingleClickIconButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  content: @Composable () -> Unit,
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  IconButton(
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    content = content,
  )
}

@Composable
fun SingleClickTextButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = null,
  shape: Shape = MaterialTheme.shapes.small,
  border: BorderStroke? = null,
  colors: ButtonColors = ButtonDefaults.textButtonColors(),
  contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
  content: @Composable RowScope.() -> Unit,
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  TextButton(
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    elevation = elevation,
    shape = shape,
    border = border,
    colors = colors,
    contentPadding = contentPadding,
    content = content,
  )
}

@Composable
fun SingleClickTab(
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  text: @Composable (() -> Unit)? = null,
  icon: @Composable (() -> Unit)? = null,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  selectedContentColor: Color = LocalContentColor.current,
  unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium),
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  Tab(
    selected = selected,
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    modifier = modifier,
    enabled = enabled,
    text = text,
    icon = icon,
    interactionSource = interactionSource,
    selectedContentColor = selectedContentColor,
    unselectedContentColor = unselectedContentColor,
  )
}

@Composable
fun SingleClickTab(
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  selectedContentColor: Color = LocalContentColor.current,
  unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium),
  content: @Composable ColumnScope.() -> Unit,
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  Tab(
    selected = selected,
    onClick = { multipleEventsCutter.processEvent { onClick() } },
    modifier = modifier,
    enabled = enabled,
    interactionSource = interactionSource,
    selectedContentColor = selectedContentColor,
    unselectedContentColor = unselectedContentColor,
    content = content,
  )
}

@Composable
fun SingleClickAlertDialog(
  onDismissRequest: () -> Unit,
  buttons: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  title: (@Composable () -> Unit)? = null,
  text: @Composable (() -> Unit)? = null,
  shape: Shape = MaterialTheme.shapes.medium,
  backgroundColor: Color = MaterialTheme.colors.surface,
  contentColor: Color = contentColorFor(backgroundColor),
  properties: DialogProperties = DialogProperties(),
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  AlertDialog(
    onDismissRequest = { multipleEventsCutter.processEvent { onDismissRequest() } },
    buttons = buttons,
    modifier = modifier,
    title = title,
    text = text,
    shape = shape,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    properties = properties,
  )
}

@Composable
fun SingleClickAlertDialog(
  onDismissRequest: () -> Unit,
  confirmButton: @Composable () -> Unit,
  modifier: Modifier = Modifier,
  dismissButton: @Composable (() -> Unit)? = null,
  title: (@Composable () -> Unit)? = null,
  text: @Composable (() -> Unit)? = null,
  shape: Shape = MaterialTheme.shapes.medium,
  backgroundColor: Color = MaterialTheme.colors.surface,
  contentColor: Color = contentColorFor(backgroundColor),
  properties: DialogProperties = DialogProperties(),
) {
  val multipleEventsCutter = remember { MultipleEventsCutter.get() }
  AlertDialog(
    onDismissRequest = { multipleEventsCutter.processEvent { onDismissRequest() } },
    confirmButton = confirmButton,
    modifier = modifier,
    dismissButton = dismissButton,
    title = title,
    text = text,
    shape = shape,
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    properties = properties,
  )
}

private const val DELAY_MILLIS = 500L

@Composable
fun SingleClickSwitch(
  colors: SwitchColors = SwitchDefaults.colors(),
  checked: Boolean = false,
  onCheckedChange: ((Boolean) -> Unit)? = {},
) {
  var enabled by remember { mutableStateOf(true) }

  LaunchedEffect(checked) {
    enabled = false
    delay(DELAY_MILLIS)
    enabled = true
  }

  Box(
    modifier = Modifier
      .width(IntrinsicSize.Min)
      .height(IntrinsicSize.Min),
  ) {
    Switch(
      colors = colors,
      checked = checked,
      onCheckedChange = {
        if (enabled) {
          onCheckedChange?.invoke(it)
        }
      },
    )
    if (enabled.not()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { /* nothing to do*/ },
          ),

      )
    }
  }
}
