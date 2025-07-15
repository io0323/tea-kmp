package com.io.tea.android.ui.common.component

import android.annotation.SuppressLint
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.model.ButtonIconType
import com.io.tea.android.ui.common.component.model.TabButtonItem
import com.io.tea.android.util.ButtonUtil.FixText

@SuppressLint("ModifierParameter")
@Composable
fun ButtonPrimary(
    isEnabled: Boolean = false,
    width: Dp? = null,
    height: Dp = 48.dp,
    shape: Shape = RoundedCornerShape(100.dp),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = if (isEnabled) TeaAppTheme.colors.Blue else TeaAppTheme.colors.Grey500
    ),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonModifier: Modifier = Modifier,
    @StringRes textResId: Int,
    @ColorRes textResColor: Color = Colors.Font.contrast,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    iconType: ButtonIconType = ButtonIconType.NONE,
    @DrawableRes icon: Int = android.R.drawable.ic_menu_close_clear_cancel,
    @ColorRes tintRes: Color = TeaAppTheme.colors.White,
    contentdescription: String = "Icon",
    onClick: () -> Unit,
) {
    val modifier = when {
        width != null -> buttonModifier.width(width)
        else -> buttonModifier
    }
    Button(
        shape = shape,
        contentPadding = contentPadding,
        elevation = elevation,
        interactionSource = interactionSource,
        colors = buttonColors,
        modifier = modifier.height(height),
        onClick = onClick,
    ) {
        ConditionalButtonIconType(
            textResId = textResId,
            textColor = textResColor,
            textStyle = textStyle,
            textModifier = textModifier,
            textAlign = textAlign,
            maxLines = maxLines,
            iconType = iconType,
            icon = icon,
            tint = tintRes,
            contentdescription = contentdescription,
        )
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun ButtonSecondary(
    isEnabled: Boolean = false,
    width: Dp? = null,
    height: Dp = 48.dp,
    shape: Shape = RoundedCornerShape(100.dp),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = if (isEnabled) TeaAppTheme.colors.White else TeaAppTheme.colors.Grey500
    ),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonModifier: Modifier = Modifier,
    @StringRes textResId: Int,
    @ColorRes textResColor: Color = TeaAppTheme.colors.BlueDark,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    textModifier: Modifier = Modifier,
    iconType: ButtonIconType = ButtonIconType.NONE,
    @DrawableRes icon: Int = android.R.drawable.ic_menu_close_clear_cancel,
    @ColorRes tintRes: Color = TeaAppTheme.colors.BlueDark,
    contentdescription: String = "Icon",
    onClick: () -> Unit,
) {
    val textColor = if (isEnabled) textResColor else TeaAppTheme.colors.White
    val tintColor = if (isEnabled) tintRes else TeaAppTheme.colors.White
    val modifier = when {
        width != null -> buttonModifier.width(width)
        else -> buttonModifier
    }
    OutlinedButton(
        shape = shape,
        border = BorderStroke(
            width = 1.dp,
            color = if (isEnabled) TeaAppTheme.colors.BlueLight else TeaAppTheme.colors.Grey500
        ),
        contentPadding = contentPadding,
        elevation = elevation,
        interactionSource = interactionSource,
        colors = buttonColors,
        modifier = modifier.height(height),
        onClick = onClick,
    ) {
        ConditionalButtonIconType(
            textResId = textResId,
            textColor = textColor,
            textStyle = textStyle,
            textModifier = textModifier,
            textAlign = textAlign,
            maxLines = maxLines,
            iconType = iconType,
            icon = icon,
            tint = tintColor,
            contentdescription = contentdescription,
        )
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun ButtonTertiary(
    isEnabled: Boolean = false,
    width: Dp? = null,
    height: Dp = 40.dp,
    shape: Shape = RoundedCornerShape(100.dp),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = if (isEnabled) TeaAppTheme.colors.White else TeaAppTheme.colors.Grey500
    ),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonModifier: Modifier = Modifier,
    @StringRes textResId: Int,
    @ColorRes textResColor: Color = TeaAppTheme.colors.BlueDark,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    textModifier: Modifier = Modifier,
    iconType: ButtonIconType = ButtonIconType.NONE,
    @DrawableRes icon: Int = android.R.drawable.ic_menu_close_clear_cancel,
    @ColorRes tintRes: Color = TeaAppTheme.colors.BlueDark,
    contentdescription: String = "Icon",
    onClick: () -> Unit,
) {
    val textColor = if (isEnabled) textResColor else TeaAppTheme.colors.White
    val tintColor = if (isEnabled) tintRes else TeaAppTheme.colors.White
    val modifier = when {
        width != null -> buttonModifier.width(width)
        else -> buttonModifier
    }
    OutlinedButton(
        shape = shape,
        border = BorderStroke(
            width = 1.dp,
            color = if (isEnabled) TeaAppTheme.colors.BlueLight else TeaAppTheme.colors.Grey500
        ),
        contentPadding = contentPadding,
        elevation = elevation,
        interactionSource = interactionSource,
        colors = buttonColors,
        modifier = modifier.height(height),
        onClick = onClick,
    ) {
        ConditionalButtonIconType(
            textResId = textResId,
            textColor = textColor,
            textStyle = textStyle,
            textModifier = textModifier,
            textAlign = textAlign,
            maxLines = maxLines,
            iconType = iconType,
            icon = icon,
            tint = tintColor,
            contentdescription = contentdescription,
        )
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun ButtonQuaternary(
    isEnabled: Boolean = false,
    width: Dp? = null,
    height: Dp = 30.dp,
    shape: Shape = RoundedCornerShape(20.dp),
    borderWidth: Dp = 1.dp,
    borderColor: Color = if (isEnabled) TeaAppTheme.colors.Grey500 else TeaAppTheme.colors.Grey500,
    contentPadding: PaddingValues = PaddingValues(
        start = 12.dp,
        top = 5.5.dp,
        end = 8.dp,
        bottom = 5.5.dp
    ),
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = if (isEnabled) TeaAppTheme.colors.White else TeaAppTheme.colors.White.copy(
            alpha = 0.3f
        )
    ),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonModifier: Modifier = Modifier,
    @StringRes textResId: Int,
    @ColorRes textResColor: Color = TeaAppTheme.colors.FontPrimary,
    textStyle: TextStyle = TeaAppTheme.typography.btn,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    textModifier: Modifier = Modifier,
    iconType: ButtonIconType = ButtonIconType.NONE,
    @DrawableRes icon: Int = android.R.drawable.ic_menu_close_clear_cancel,
    @ColorRes tintRes: Color = TeaAppTheme.colors.BlueDark,
    contentdescription: String = "Icon",
    onClick: () -> Unit,
) {
    // TODO : Figma値と異なる
    val textColor = if (isEnabled) textResColor else TeaAppTheme.colors.Grey500
    val tintColor = if (isEnabled) tintRes else tintRes.copy(alpha = 0.3f)
    val modifier = when {
        width != null -> buttonModifier.width(width)
        else -> buttonModifier
    }
    OutlinedButton(
        shape = shape,
        border = BorderStroke(
            width = borderWidth,
            color = borderColor
        ),
        contentPadding = contentPadding,
        elevation = elevation,
        interactionSource = interactionSource,
        colors = buttonColors,
        modifier = modifier.height(height),
        onClick = onClick,
    ) {
        ConditionalButtonIconType(
            textResId = textResId,
            textColor = textColor,
            textStyle = textStyle,
            textModifier = textModifier,
            textAlign = textAlign,
            maxLines = maxLines,
            iconType = iconType,
            icon = icon,
            tint = tintColor,
            contentdescription = contentdescription,
        )
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun TextButton(
    isEnabled: Boolean = false,
    @StringRes textResId: Int,
    @ColorRes textResColor: Color = TeaAppTheme.colors.BlueDark,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    textModifier: Modifier = Modifier,
    iconType: ButtonIconType = ButtonIconType.NONE,
    @DrawableRes icon: Int = android.R.drawable.ic_menu_close_clear_cancel,
    @ColorRes tintRes: Color = TeaAppTheme.colors.BlueDark,
    contentdescription: String = "Icon",
    onClick: () -> Unit,
) {
    val textColor = if (isEnabled) textResColor else TeaAppTheme.colors.Grey500
    val tintColor = if (isEnabled) tintRes else TeaAppTheme.colors.Grey500
    val modifier = if (isEnabled) Modifier.clickable(onClick = onClick) else Modifier

    ConditionalButtonIconType(
        textResId = textResId,
        textColor = textColor,
        textStyle = textStyle,
        textModifier = textModifier,
        textAlign = textAlign,
        maxLines = maxLines,
        iconType = iconType,
        icon = icon,
        tint = tintColor,
        modifier = modifier,
        contentdescription = contentdescription,
    )
}

@SuppressLint("ModifierParameter")
@Composable
fun ButtonChevron(
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        enabled = isEnabled,
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TeaAppTheme.colors.White90
        ),
        modifier = modifier
            .width(44.dp)
            .height(35.dp)
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "",
            tint = TeaAppTheme.colors.FontPrimary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ButtonClose(
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(
        enabled = isEnabled,
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp,
            color = TeaAppTheme.colors.Grey500
        ),
        contentPadding = PaddingValues(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TeaAppTheme.colors.White
        ),
        modifier = Modifier.size(32.dp)
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "",
            tint = TeaAppTheme.colors.FontPrimary,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ButtonSearch(
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    ButtonQuaternary(
        height = 38.dp,
        borderWidth = 2.dp,
        icon = android.R.drawable.ic_menu_close_clear_cancel,
        iconType = ButtonIconType.LEFT,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 7.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
        textResId = R.string.button__search,
        textStyle = TeaAppTheme.typography.h4,
        textResColor = TeaAppTheme.colors.BlueDark,
        isEnabled = isEnabled,
        onClick = onClick,
    )
}

@Composable
fun ButtonRoute(
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    ButtonDetail(
        isEnabled = isEnabled,
        iconResId = android.R.drawable.ic_menu_close_clear_cancel,
        textResId = R.string.button__route,
        onClick = onClick
    )
}

@Composable
fun ButtonPhone(
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    ButtonDetail(
        isEnabled = isEnabled,
        iconResId = android.R.drawable.ic_menu_close_clear_cancel,
        textResId = R.string.button__phone,
        onClick = onClick
    )
}

@Composable
fun ButtonHomePage(
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    ButtonDetail(
        isEnabled = isEnabled,
        iconResId = android.R.drawable.ic_menu_close_clear_cancel,
        textResId = R.string.button__home_page,
        onClick = onClick
    )
}


@SuppressLint("ModifierParameter")
@Composable
private fun ConditionalButtonIconType(
    @StringRes textResId: Int,
    @ColorRes textColor: Color = Colors.Font.contrast,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    iconType: ButtonIconType = ButtonIconType.NONE,
    @DrawableRes icon: Int,
    @ColorRes tint: Color = TeaAppTheme.colors.White,
    modifier: Modifier = Modifier,
    contentdescription: String = "Icon",
) {
    when (iconType) {
        ButtonIconType.LEFT -> {
            IconLeft(
                textResId = textResId,
                textColor = textColor,
                textStyle = textStyle,
                textModifier = textModifier,
                textAlign = textAlign,
                maxLines = maxLines,
                icon = icon,
                tint = tint,
                modifier = modifier,
                contentdescription = contentdescription,
            )
        }

        ButtonIconType.RIGHT -> {
            IconRight(
                textResId = textResId,
                textColor = textColor,
                textStyle = textStyle,
                textModifier = textModifier,
                textAlign = textAlign,
                maxLines = maxLines,
                icon = icon,
                tint = tint,
                modifier = modifier,
                contentdescription = contentdescription,
            )
        }

        else -> {
            IconNone(
                textResId = textResId,
                textColor = textColor,
                textStyle = textStyle,
                textModifier = textModifier,
                textAlign = textAlign, maxLines = maxLines,
            )
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
private fun IconNone(
    @StringRes textResId: Int,
    @ColorRes textColor: Color = Colors.Font.contrast,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
) {
    FixText(
        text = stringResource(textResId),
        color = textColor,
        textStyle = textStyle,
        textAlign = textAlign,
        maxLines = maxLines,
        modifier = textModifier
    )
}

@SuppressLint("ModifierParameter")
@Composable
private fun IconLeft(
    @StringRes textResId: Int,
    @ColorRes textColor: Color = Colors.Font.contrast,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    @DrawableRes icon: Int,
    @ColorRes tint: Color = TeaAppTheme.colors.White,
    modifier: Modifier = Modifier,
    contentdescription: String = "Icon",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(end = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentdescription,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        FixText(
            text = stringResource(textResId),
            color = textColor,
            textStyle = textStyle,
            textAlign = textAlign,
            maxLines = maxLines,
            modifier = textModifier
        )
    }
}

@SuppressLint("ModifierParameter")
@Composable
private fun IconRight(
    @StringRes textResId: Int,
    @ColorRes textColor: Color = Colors.Font.contrast,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    @DrawableRes icon: Int,
    @ColorRes tint: Color = TeaAppTheme.colors.White,
    modifier: Modifier = Modifier,
    contentdescription: String = "Icon",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        FixText(
            text = stringResource(textResId),
            color = textColor,
            textStyle = textStyle,
            textAlign = textAlign,
            maxLines = maxLines,
            modifier = textModifier
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentdescription,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun ButtonDetail(
    isEnabled: Boolean = true,
    @DrawableRes iconResId: Int,
    @StringRes textResId: Int,
    onClick: () -> Unit,
) {
    Button(
        enabled = isEnabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = TeaAppTheme.colors.BlueHighlight
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        modifier = Modifier.height(36.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = textResId),
                style = TeaAppTheme.typography.label,
                color = TeaAppTheme.colors.BlueDark
            )
        }
    }
}

@Composable
fun ButtonSort(
    isEnabled: Boolean = true,
    isSort: Boolean = false,
    onClick: (isSort: Boolean) -> Unit,
) {
    ButtonQuaternary(
        height = 38.dp,
        borderWidth = 1.dp,
        borderColor = TeaAppTheme.colors.Grey500,
        icon = android.R.drawable.ic_menu_close_clear_cancel,
        iconType = ButtonIconType.LEFT,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 7.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp),
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) TeaAppTheme.colors.White else TeaAppTheme.colors.Grey500
        ),
        textResId = R.string.button__sort,
        textStyle = TeaAppTheme.typography.h4,
        textResColor = TeaAppTheme.colors.FontPrimary,
        tintRes = Color.Unspecified,
        isEnabled = isEnabled,
        onClick = { onClick(isSort) },
    )
}

@Composable
fun ButtonFilter(
    isEnabled: Boolean = true,
    isFilter: Boolean = false,
    onClick: () -> Unit,
) {
    ButtonQuaternary(
        height = 38.dp,
        borderWidth = 1.dp,
        borderColor = TeaAppTheme.colors.Grey500,
        icon = if (isFilter) android.R.drawable.ic_menu_sort_by_size else android.R.drawable.ic_menu_sort_alphabetically,
        iconType = ButtonIconType.LEFT,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 7.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp),
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) TeaAppTheme.colors.White else TeaAppTheme.colors.Grey500
        ),
        textResId = R.string.button__filter,
        textStyle = TeaAppTheme.typography.h4,
        textResColor = TeaAppTheme.colors.FontPrimary,
        tintRes = Color.Unspecified,
        isEnabled = isEnabled,
        onClick = onClick,
    )
}

@Composable
fun TabButton(
    tabList: List<TabButtonItem>,
    modifier: Modifier = Modifier,
    onClick: (TabButtonItem) -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        tabList.mapIndexed { index, item ->
            val buttonColor = when {
                item.isSelected -> TeaAppTheme.colors.Blue
                else -> TeaAppTheme.colors.Grey100
            }
            val textColor = when {
                item.isSelected -> TeaAppTheme.colors.White
                else -> TeaAppTheme.colors.FontPrimary
            }
            val textStyle = when {
                item.isSelected -> TeaAppTheme.typography.btnL
                else -> TeaAppTheme.typography.btn
            }
            ButtonPrimary(
                textResId = tabList[index].text,
                height = 30.dp,
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
                shape = RoundedCornerShape(20.dp),
                buttonModifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                textResColor = textColor,
                textStyle = textStyle,
                onClick = { onClick(tabList[index]) },
            )
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun RightEndButton(
    isEnabled: Boolean = false,
    width: Dp? = null,
    height: Dp = 48.dp,
    shape: Shape = RoundedCornerShape(100.dp),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = if (isEnabled) TeaAppTheme.colors.White else TeaAppTheme.colors.Grey500
    ),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonModifier: Modifier = Modifier,
    @StringRes textResId: Int,
    @ColorRes textResColor: Color = Colors.Font.contrast,
    textStyle: TextStyle = TeaAppTheme.typography.btnL,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = 1,
    iconType: ButtonIconType = ButtonIconType.NONE,
    @DrawableRes icon: Int = android.R.drawable.ic_menu_close_clear_cancel,
    @ColorRes tintRes: Color = TeaAppTheme.colors.White,
    contentdescription: String = "Icon",
    onClick: () -> Unit,
) {
    val btnModifier = if (width == null) {
        buttonModifier
            .height(height)
            .wrapContentWidth()
    } else {
        buttonModifier.defaultMinSize(minWidth = width, minHeight = height)
    }
    Button(
        shape = shape,
        contentPadding = contentPadding,
        elevation = elevation,
        interactionSource = interactionSource,
        colors = buttonColors,
        modifier = btnModifier,
        onClick = onClick,
    ) {
        ConditionalButtonIconType(
            textResId = textResId,
            textColor = textResColor,
            textStyle = textStyle,
            textModifier = textModifier,
            textAlign = textAlign,
            maxLines = maxLines,
            iconType = iconType,
            icon = icon,
            tint = tintRes,
            contentdescription = contentdescription,
        )
    }
}