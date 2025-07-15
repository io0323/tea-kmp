package com.io.tea.android.ui.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.TextButton

@SuppressLint("SetjavaScriptEnabled")
@Composable
fun TextDialog(
    title: String,
    text: String,
    onDismissClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    var isEnabled by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = { onDismissClick() },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        ),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 24.dp, horizontal = 20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = title,
                        style = TeaAppTheme.typography.h3,
                        color = TeaAppTheme.colors.FontPrimary,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = text,
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(state = scrollState)
                                .onGloballyPositioned {
                                    val height = it.size.height
                                    val boundsHeight = it.boundsInParent().height
                                    if (height <= boundsHeight) {
                                        isEnabled = true
                                    }
                                }
                        )
                    }
                    TextButton(
                        textResId = R.string.common__close,
                        onClick = { onDismissClick() },
                        isEnabled = isEnabled,
                    )
                }
            }
        }
    )

    LaunchedEffect(scrollState.value) {
        val maxScroll = scrollState.maxValue
        val currentScroll = scrollState.value
        val scrollPercentage = currentScroll / maxScroll.toFloat()
        if (scrollPercentage >= 0.95f) {
            isEnabled = true
        }
    }
}