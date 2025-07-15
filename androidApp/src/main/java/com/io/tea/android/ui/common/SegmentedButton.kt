package com.io.tea.android.ui.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
internal fun SegmentedButton(
    leftLabel: String,
    rightLabel: String,
    isSelectedLeft: Boolean,
    onClickLeft: () -> Unit,
    onClickRight: () -> Unit,
    animationDurationMillis: Int = 300,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val animationSpec = TweenSpec<Dp>(durationMillis = animationDurationMillis)

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val screenWidth = maxWidth
        val buttonWidth = (screenWidth - 6.dp) / 2
        val offsetX by animateDpAsState(
            targetValue = if (isSelectedLeft) 6.dp else buttonWidth,
            animationSpec = animationSpec,
            label = ""
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Colors.SegmentedButtonBackground)
                .width(screenWidth)
                .height(43.dp),
        ) {
            // アニメーションする背景
            Box(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .offset(x = offsetX)
                    .width(buttonWidth)
                    .height(33.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Colors.White)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { onClickLeft() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = leftLabel,
                        color = if (isSelectedLeft) Colors.BlueDark else Colors.FontSecondary,
                        style = TeaAppTheme.typography.h4
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { onClickRight() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = rightLabel,
                        color = if (isSelectedLeft) Colors.FontSecondary else Colors.BlueDark,
                        style = TeaAppTheme.typography.h4
                    )
                }
            }
        }
    }
}
