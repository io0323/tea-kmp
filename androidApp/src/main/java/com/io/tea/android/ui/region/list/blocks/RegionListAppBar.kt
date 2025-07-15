package com.io.tea.android.ui.region.list.blocks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegionListAppBar(
    isDeleteButtonEnabled: Boolean = false,
    onDeleteButtonClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.region_list__header_title),
                style = TeaAppTheme.typography.regionListTitle,
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            TextButton(
                enabled = false,
                onClick = onDeleteButtonClick
            ) {
                val style = if (isDeleteButtonEnabled) {
                    TeaAppTheme.typography.regionListDeleteEnable
                } else {
                    TeaAppTheme.typography.regionListDeleteDisable
                }

                Text(
                    text = stringResource(R.string.region_list__header_button_title),
                    style = style,
                )
            }
        }
    )
}
