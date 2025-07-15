package com.io.tea.android.ui.region.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.region.home.block.BannerBlock
import com.io.tea.android.ui.region.home.block.BusinessNumberBlock
import com.io.tea.android.ui.region.home.block.CampaignBlock
import com.io.tea.android.ui.region.home.block.ChargeAndPaymentBlock
import com.io.tea.android.ui.region.home.block.PeriodBlock
import com.io.tea.android.ui.region.home.block.PointBlock
import com.io.tea.android.ui.region.home.block.TopBannerBlock
import com.io.tea.android.nav.bottom.BottomBar
import com.io.tea.android.nav.bottom.OnClickBottomNavItem
import com.io.tea.android.ui.common.PaySwitch
import com.io.tea.android.ui.common.block.NotificationBlock
import com.io.tea.android.ui.notice.common.NoticeModel
import com.io.tea.android.ui.region.home.model.RegionHomeModel

@Composable
internal fun RegionHomeScreen(
    regionHomeModel: RegionHomeModel,
    onPopBack: () -> Unit,
    onPeriodDetailClick: () -> Unit,
    onPointClick: () -> Unit,
    onChargeClick: () -> Unit,
    onPaymentClick: () -> Unit,
    onBusinessNumberClick: () -> Unit,
    onCampaignClick: () -> Unit,
    onBannerClick: (String) -> Unit,
    onNoticeDetailClick: () -> Unit,
    onNoticeItemClick: (NoticeModel) -> Unit,
    onClickBottomNavItem: OnClickBottomNavItem,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            BottomBar(onClickBottomNavItem = onClickBottomNavItem)
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
            ) {
                TopBannerBlock(
                    topBannerModel = regionHomeModel.topBannerModel,
                    onClick = onPopBack,
                )
                PeriodBlock(
                    periodModel = regionHomeModel.periodModel,
                    onDetailClick = onPeriodDetailClick,
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(regionHomeModel.pointModel.backgroundColor)
                        .padding(horizontal = 24.dp)
                        .padding(top = 12.dp, bottom = 13.dp)
                        .fillMaxWidth()
                ) {
                    PointBlock(
                        pointModel = regionHomeModel.pointModel,
                        onPointClick = onPointClick,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ChargeAndPaymentBlock(
                        chargeAndPaymentModel = regionHomeModel.chargeAndPaymentModel,
                        onChargeClick = onChargeClick,
                        onPaymentClick = onPaymentClick,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    BusinessNumberBlock(
                        onClick = onBusinessNumberClick,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    CampaignBlock(
                        campaignModel = regionHomeModel.campaignModel,
                        onClick = onCampaignClick
                    )
                }

                BannerBlock(
                    bannerModel = regionHomeModel.bannerModel,
                    onClick = onBannerClick
                )
                NotificationBlock(
                    notificationModel = regionHomeModel.notificationModel,
                    onDetailClick = onNoticeDetailClick,
                    onNoticeItemClick = onNoticeItemClick
                )
            }
            PaySwitch(
                onClick = {},
                buttonModifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 14.dp),
            )
        }
    }
}
