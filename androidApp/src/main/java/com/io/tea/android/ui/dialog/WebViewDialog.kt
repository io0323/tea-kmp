package com.io.tea.android.ui.dialog

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.TextButton

@SuppressLint("SetjavaScriptEnabled")
@Composable
fun WebViewDialog(
    title: String,
    url: String,
    onDismissClick: () -> Unit,
) {
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
                    .fillMaxHeight()
                    .padding(vertical = 20.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 24.dp),
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
                        WebViewContent(
                            url = url,
                            onScrollEnd = { isScrollEnd ->
                                if (!isEnabled) {
                                    isEnabled = isScrollEnd
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
}

@Composable
fun WebViewContent(
    url: String,
    onScrollEnd: (Boolean) -> Unit,
) {
    var isWebViewLoaded by remember { mutableStateOf(false) }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        isWebViewLoaded = true
                    }
                }
                loadUrl(url)
                setOnScrollChangeListener { _, _, scrollY, _, _ ->
                    val contentHeight = (contentHeight * resources.displayMetrics.density) * 0.95
                    val viewHeight = height.toFloat()
                    val isScrollEnd = scrollY + viewHeight >= contentHeight
                    onScrollEnd(isScrollEnd && isWebViewLoaded)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
