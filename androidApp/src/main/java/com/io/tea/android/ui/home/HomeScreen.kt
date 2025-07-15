package com.io.tea.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.common.LoadIndicator
import com.io.tea.android.ui.home.block.HomeTeaCardBlock
import com.io.tea.android.ui.home.block.HomeSectionHeaderBlock
import com.io.tea.android.ui.home.block.HomeTopBarBlock
import com.io.tea.android.ui.home.state.HomeDisplayState
import com.io.tea.android.ui.common.block.NotificationBlock
import com.io.tea.android.ui.home.block.HomeBannerBlock
import com.io.tea.android.ui.home.model.BannerModel
import com.io.tea.android.ui.home.model.TeaModel
import com.io.tea.android.ui.home.state.HomeUiState
import com.io.tea.android.ui.home.state.HomeUseCaseState
import com.io.tea.android.ui.notice.common.NoticeModel

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    displayState: HomeDisplayState,
    uiState: HomeUiState,
    useCaseState: HomeUseCaseState,
    onClickSetting: () -> Unit,
    onClickMyRegion: () -> Unit,
    onClickBannerItem: (BannerModel) -> Unit,
    onClickTeaItem: (TeaModel) -> Unit,
    onClickTeaAppSearch: () -> Unit,
    onClickNoticeDetail: () -> Unit,
    onClickNoticeItem: (NoticeModel) -> Unit,

    ) {
    val scrollState = rememberScrollState()
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                when (useCaseState) {
                    is HomeUseCaseState.Failure -> {}
                    is HomeUseCaseState.Initial -> {
                        LoadIndicator(Modifier.fillMaxSize())
                    }

                    is HomeUseCaseState.Loading -> {
                        LoadIndicator(Modifier.fillMaxSize())
                    }

                    is HomeUseCaseState.Success -> {
                        val bannerList = useCaseState.bannerList
                        val teaList = useCaseState.teaList
                        val notificationModel = useCaseState.notificationModel
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            HomeTopBarBlock(
                                onClickSetting = onClickSetting,
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.verticalScroll(scrollState)
                            ) {
                                HomeSectionHeaderBlock(
                                    display = displayState,
                                    onClickMyRegion = onClickMyRegion,
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                HomeBannerBlock(
                                    bannerList = bannerList,
                                    onClickItem = onClickBannerItem,
                                )
                                HomeTeaCardBlock(
                                    displayType = displayState.displayType,
                                    teaList = teaList,
                                    onClickTeaItem = onClickTeaItem,
                                    onClickSearch = onClickTeaAppSearch,
                                )
                                NotificationBlock(
                                    notificationModel = notificationModel,
                                    onDetailClick = onClickNoticeDetail,
                                    onNoticeItemClick = onClickNoticeItem
                                )
                            }
                        }
                    }

                    is HomeUseCaseState.Success -> TODO()
                    HomeUseCaseState.Initial -> TODO()
                    HomeUseCaseState.Loading -> TODO()
                    is HomeUseCaseState.Failure -> TODO()
                    else -> {}
                }
            }
        }
    }
}