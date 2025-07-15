package com.io.tea.android.ui.region.detail.block

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.region.detail.RegionDetailViewModel
import com.io.tea.android.ui.region.detail.model.RegionDetailModel
import com.io.tea.android.util.TextUtil

@Composable
fun CampaignBlock(
    viewModel: RegionDetailViewModel,
    model: RegionDetailModel,
) {
    Column {
        Text(
            text = stringResource(id = R.string.region_detail__campaign_name),
            style = TeaAppTheme.typography.labelL,
            color = TeaAppTheme.colors.FontSecondary,
        )
        Text(
            text = model.campaignName,
            style = TeaAppTheme.typography.h1,
            color = TeaAppTheme.colors.FontPrimary,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.region_detail__campaign_detail),
            style = TeaAppTheme.typography.labelL,
            color = TeaAppTheme.colors.FontSecondary,
        )
        Text(
            text = model.campaignDetail,
            style = TeaAppTheme.typography.h6,
            color = TeaAppTheme.colors.FontPrimary,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.region_detail__campaign_period),
            style = TeaAppTheme.typography.labelL,
            color = TeaAppTheme.colors.FontSecondary,
        )
        Text(
            text = model.campaignPeriodStart + "〜" + model.campaignPeriodEnd,
            style = TeaAppTheme.typography.h3,
            color = TeaAppTheme.colors.FontPrimary,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.region_detail__point_expiration_date),
            style = TeaAppTheme.typography.labelL,
            color = TeaAppTheme.colors.FontSecondary,
        )
        Text(
            text = stringResource(
                R.string.region_detail__campaign_period_start,
                model.pointExpirationStart,
            ),
            style = TeaAppTheme.typography.h4,
            color = TeaAppTheme.colors.FontPrimary,
        )
        Text(
            text = stringResource(
                R.string.region_detail__campaign_period_end,
                model.pointExpirationEnd,
            ),
            style = TeaAppTheme.typography.h4,
            color = TeaAppTheme.colors.FontPrimary,
        )
        Text(
            text = model.pointExpirationNote,
            style = TeaAppTheme.typography.caption,
            color = TeaAppTheme.colors.FontSecondary,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.region_detail__campaign_site),
            style = TeaAppTheme.typography.labelL,
            color = TeaAppTheme.colors.FontSecondary,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { viewModel.onClickLink(model.campaignSiteURL) }
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "link",
                tint = TeaAppTheme.colors.BlueDark,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextUtil.LinkText(
                text = model.campaignSiteName,
                linkedText = model.campaignSiteName,
                link = model.campaignSiteURL,
                textFontStyle = TeaAppTheme.typography.btn,
                linkFontStyle = TeaAppTheme.typography.btn,
                onClick = { viewModel.onClickLink(model.campaignSiteURL) }
            )
        }
    }
}
