package com.io.tea.android.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonPrimary
import com.io.tea.android.ui.common.component.ButtonSecondary
import com.io.tea.android.util.TextUtil

@Composable
internal fun BottomBar(
    @StringRes resourceId: Int,
    onClick: () -> Unit,
    enabled: Boolean = true,
    buttonType: TeaAppBottomButtonType = TeaAppBottomButtonType.PRIMARY,
) {
    // ナビゲーションバーの高さを取得
    val bottomPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    val buttonModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 40.dp, vertical = 16.dp)
        .padding(bottom = bottomPadding)

    Column {
        TeaAppHorizontalDivider()

        when (buttonType) {
            TeaAppBottomButtonType.PRIMARY -> {
                ButtonPrimary(
                    isEnabled = enabled,
                    textResId = resourceId,
                    onClick = onClick,
                    buttonModifier = buttonModifier,
                )
            }

            TeaAppBottomButtonType.SECONDARY -> {
                ButtonSecondary(
                    isEnabled = enabled,
                    textResId = resourceId,
                    onClick = onClick,
                    buttonModifier = buttonModifier
                )
            }
        }
    }
}

@Composable
internal fun BottomBar(
    @StringRes resourceIdLeft: Int,
    @StringRes resourceIdRight: Int,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit,
    isLeftButtonEnabled: Boolean = false,
    isRightButtonEnabled: Boolean = false,
) {
    Column {
        TeaAppHorizontalDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            ButtonSecondary(
                buttonModifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 16.dp,
                        start = 24.dp,
                        bottom = 16.dp,
                    )
                    .height(48.dp),
                isEnabled = isLeftButtonEnabled,
                textResId = resourceIdLeft,
                onClick = onClickLeft,
            )
            Spacer(modifier = Modifier.width(16.dp))
            ButtonPrimary(
                buttonModifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 16.dp,
                        end = 25.dp,
                        bottom = 16.dp,
                    )
                    .height(48.dp),
                isEnabled = isRightButtonEnabled,
                textResId = resourceIdRight,
                onClick = onClickRight,
            )
        }
    }
}

@Composable
internal fun TeaAppBottomButtonWithWeight(
    @StringRes resourceIdLeft: Int,
    @StringRes resourceIdRight: Int,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit,
    isLeftButtonEnabled: Boolean = false,
    isRightButtonEnabled: Boolean = false,
    leftButtonWeight: Float = 1.0f,
    rightButtonWeight: Float = 1.7678572f, // 198 / 112 = 1.7678572
) {
    // ナビゲーションバーの高さを取得
    val bottomPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()

    Column {
        TeaAppHorizontalDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .padding(bottom = bottomPadding)
        ) {
            ButtonSecondary(
                buttonModifier = Modifier
                    .weight(leftButtonWeight)
                    .height(48.dp),
                isEnabled = isLeftButtonEnabled,
                textResId = resourceIdLeft,
                onClick = onClickLeft,
            )
            Spacer(modifier = Modifier.width(16.dp))
            ButtonPrimary(
                buttonModifier = Modifier
                    .weight(rightButtonWeight)
                    .height(48.dp),
                isEnabled = isRightButtonEnabled,
                textResId = resourceIdRight,
                onClick = onClickRight,
            )
        }
    }
}

@Composable
internal fun BottomBar(
    @StringRes resourceIdLinkText: Int,
    @StringRes resourceIdButton: Int,
    isChecked: Boolean,
    linkedText: String,
    linkTextUrl: String,
    isButtonEnabled: Boolean = false,
    onChecked: () -> Unit,
    onClickLink: () -> Unit,
    onClickButton: () -> Unit,
) {
    Column {
        TeaAppHorizontalDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onChecked() }
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { onChecked() },
                modifier = Modifier.size(24.dp)
            )
            TextUtil.LinkText(
                text = stringResource(resourceIdLinkText),
                linkedText = linkedText,
                link = linkTextUrl,
                textFontStyle = TeaAppTheme.typography.h6,
                linkFontStyle = TeaAppTheme.typography.h6,
                onClick = onClickLink
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ButtonPrimary(
                buttonModifier = Modifier
                    .weight(1f)
                    .padding(
                        top = 18.dp,
                        start = 40.dp,
                        end = 40.dp,
                        bottom = 36.dp,
                    )
                    .height(48.dp),
                isEnabled = isButtonEnabled,
                textResId = resourceIdButton,
                onClick = onClickButton,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

enum class TeaAppBottomButtonType {
    PRIMARY,
    SECONDARY,
}
