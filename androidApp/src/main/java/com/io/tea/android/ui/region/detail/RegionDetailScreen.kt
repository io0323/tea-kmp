package com.io.tea.android.ui.region.detail

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.ui.common.LoadIndicator
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.dialog.TextDialog
import com.io.tea.android.ui.region.detail.block.CampaignBlock
import com.io.tea.android.ui.region.detail.block.CouponImage
import com.io.tea.android.ui.region.detail.state.AgreeState
import com.io.tea.android.ui.region.detail.state.RegionDetailUseCaseState

@Composable
fun RegionDetailScreen(
    viewModel: RegionDetailViewModel,
    useCase: RegionDetailUseCaseState,
    agree: AgreeState,
    isConfirmButtonEnabled: Boolean,
) {
    val scrollState = rememberScrollState()
    var isVisibleDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.delete_region_confirm__header_title),
                onPopBack = { viewModel.onPopBack() },
            )
        },
        bottomBar = {
            when (useCase) {
                is RegionDetailUseCaseState.Success -> {
                    BottomBar(
                        resourceIdLinkText = agree.resText,
                        resourceIdButton = R.string.common__add,
                        isChecked = agree.isChecked,
                        isButtonEnabled = isConfirmButtonEnabled,
                        linkedText = agree.linkedText,
                        linkTextUrl = agree.linkTextUrl,
                        onChecked = { viewModel.onCheckedTosAgree(agree.isChecked) },
                        onClickLink = { isVisibleDialog = true },
                        onClickButton = { viewModel.onClickAdd() }
                    )
                }
                else -> {}
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (useCase) {
                is RegionDetailUseCaseState.Failure -> {}
                is RegionDetailUseCaseState.Initial -> {}
                is RegionDetailUseCaseState.Loading -> {
                    LoadIndicator(Modifier.fillMaxSize())
                }

                is RegionDetailUseCaseState.Success -> {
                    val model = useCase.data
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        CouponImage(
                            imageUrl = model.imageUrl,
                            contentDescription = model.description,
                            onClick = { viewModel.onClickLink(model.imageUrl) }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier.fillMaxSize()
                            .padding(horizontal = 24.dp)
                        ) {
                            CampaignBlock(
                                viewModel = viewModel,
                                model = model,
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    if (isVisibleDialog) {
                        TextDialog(
                            title = model.tosTitle ,
                            text = model.tosText,
                            onDismissClick = { isVisibleDialog = false },
                        )
                    }
                }
            }
        }
    }
}
