package com.io.tea.android.ui.region.home.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
internal fun BusinessNumberBlock(
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = TeaAppTheme.colors.White),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Text(
                text = stringResource(id = R.string.region_home__business_number),
                style = TeaAppTheme.typography.h4,
                color = TeaAppTheme.colors.FontPrimary,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "rectangle",
                modifier = Modifier.size(20.dp),
            )
        }
    }
}
