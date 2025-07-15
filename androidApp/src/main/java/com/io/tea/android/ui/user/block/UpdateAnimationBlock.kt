package com.io.tea.android.ui.user.block

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun UpdateAnimationBlock(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    offsetY: Int,
    durationMillis: Int,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { _ -> offsetY },
            animationSpec = tween(durationMillis = durationMillis)
        ),
        exit = slideOutVertically(
            targetOffsetY = { _ -> offsetY },
            animationSpec = tween(durationMillis = durationMillis)
        ),
        modifier = modifier
    ) {
        UpdateMessage(message = stringResource(R.string.user_information__success_toast))
    }
}

@Composable
fun UpdateMessage(message: String, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(TeaAppTheme.colors.BlueHighlight)
        ) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "success",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = message,
                style = TeaAppTheme.typography.h4,
                color = TeaAppTheme.colors.FontPrimary,
            )
        }
    }
}
