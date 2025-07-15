package com.io.tea.android.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.io.tea.android.resource.theme.TeaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String? = null,
    actionsTitle: String? = null,
    actionsTextStyle: TextStyle = LocalTextStyle.current,
    actionsEnabled: Boolean = false,
    onPopBack: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                title?.let {
                    Text(
                        text = it,
                        style = TeaAppTheme.typography.h3,
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                IconButton(onClick = { onPopBack() }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Blue
                    )
                }
            },
            actions = {
                TextButton(
                    enabled = actionsEnabled,
                    onClick = onClick
                ) {
                    actionsTitle?.let {
                        Text(
                            text = it,
                            style = actionsTextStyle,
                        )
                    }
                }
            }
        )

        TeaAppHorizontalDivider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                title?.let {
                    Text(
                        text = it,
                        style = TeaAppTheme.typography.h3,
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            actions = actions
        )

        TeaAppHorizontalDivider()
    }
}