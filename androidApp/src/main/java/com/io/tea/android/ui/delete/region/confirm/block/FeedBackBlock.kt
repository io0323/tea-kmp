package com.io.tea.android.ui.delete.region.confirm.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun FeedBackBlock(
    feedBack: String?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = TeaAppTheme.colors.Grey50,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(10.dp)
    ) {
        feedBack?.let {
            Column {
                Text(
                    text = stringResource(id = R.string.delete_region_confirm__feed_back),
                    color = TeaAppTheme.colors.FontSecondary,
                    style = TeaAppTheme.typography.label
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = TeaAppTheme.colors.FontPrimary,
                    style = TeaAppTheme.typography.h4
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}