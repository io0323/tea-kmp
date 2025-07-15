package com.io.tea.android.ui.delete.region.confirm

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.ui.delete.region.confirm.block.AgreeBlock
import com.io.tea.android.ui.delete.region.confirm.block.CautionBlock
import com.io.tea.android.ui.delete.region.confirm.block.DeletePayBlock
import com.io.tea.android.ui.delete.region.confirm.block.DeleteReasonBlock
import com.io.tea.android.ui.delete.region.confirm.block.FeedBackBlock
import com.io.tea.android.ui.delete.region.confirm.block.GuidanceBlock
import com.io.tea.android.R
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeleteRegionConfirmScreen(
    viewModel: DeleteRegionConfirmViewModel = koinViewModel(),
    deletePay: String?,
    deleteReasonList: List<String>,
    cautionList: List<Int>,
    feedBack: String?,
    isChecked: Boolean,
    isConfirmButtonEnabled: Boolean,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.delete_region_confirm__header_title),
                onPopBack = { viewModel.onPopBack() },
            )
        },
        bottomBar = {
            BottomBar(
                resourceIdLeft = R.string.common__fix,
                resourceIdRight = R.string.common__leave,
                isLeftButtonEnabled = true,
                isRightButtonEnabled = isConfirmButtonEnabled,
                onClickLeft = { viewModel.onClickCancel() },
                onClickRight = { viewModel.onClickConfirm() }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                GuidanceBlock()
                Spacer(modifier = Modifier.height(24.dp))
                DeletePayBlock(
                    deletePay = deletePay
                )
                DeleteReasonBlock(
                    list = deleteReasonList
                )
                Spacer(modifier = Modifier.height(6.dp))
                FeedBackBlock(
                    feedBack = feedBack,
                )
                CautionBlock(
                    list = cautionList
                )
                Spacer(modifier = Modifier.height(24.dp))
                AgreeBlock(
                    viewModel = viewModel,
                    isChecked = isChecked
                )
                Spacer(modifier = Modifier.height(21.dp))
            }
        }
    }
}