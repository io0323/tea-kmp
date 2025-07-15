package com.io.tea.android.ui.region.home.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.region.home.model.CampaignModel

@Composable
internal fun CampaignBlock(
    campaignModel: CampaignModel,
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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Column {
                if (campaignModel.status.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = TeaAppTheme.colors.LabelBackground,
                                shape = RoundedCornerShape(2.dp)
                            )
                            .padding(
                                horizontal = 7.78.dp,
                                vertical = 2.dp
                            )
                    ) {
                        Text(
                            text = campaignModel.status,
                            style = TeaAppTheme.typography.label,
                            color = TeaAppTheme.colors.FontContrast,
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
                Text(
                    text = campaignModel.title,
                    style = TeaAppTheme.typography.h4,
                    color = TeaAppTheme.colors.FontPrimary,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "icon",
                modifier = Modifier.size(20.dp),
            )
        }
    }
}
