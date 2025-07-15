package com.io.tea.android.ui.search.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun MyRegionEmptyBlock() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "description",
            modifier = Modifier
                .width(102.dp)
                .height(86.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = stringResource(R.string.region_list__empty_region_label),
            textAlign = TextAlign.Center,
            style = TeaAppTheme.typography.h3,
            color = TeaAppTheme.colors.FontSecondary
        )
    }
}
