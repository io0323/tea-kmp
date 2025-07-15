package com.io.tea.android.ui.delete.region.form

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
import com.io.tea.android.ui.delete.region.form.block.DeletePayBlock
import com.io.tea.android.ui.delete.region.form.block.DeleteReasonBlock
import com.io.tea.android.ui.delete.region.form.block.FeedBackBlock
import com.io.tea.android.ui.delete.region.form.block.GuidanceBlock
import com.io.tea.android.ui.delete.region.form.model.DeletePayItem
import com.io.tea.android.ui.delete.region.form.model.DeleteReasonItem
import com.io.tea.android.R
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeleteRegionFormScreen(
    viewModel: DeleteRegionFormViewModel = koinViewModel(),
    deletePayList: List<DeletePayItem>,
    deleteReasonList: List<DeleteReasonItem>,
    feedBack: String,
    isError: Boolean,
    isConfirmButtonEnabled: Boolean,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.region_list__header_button_title),
                onPopBack = { viewModel.onPopBack() },
            )
        },
        bottomBar = {
            BottomBar(
                resourceIdLeft = R.string.common__cancel,
                resourceIdRight = R.string.common__confirm,
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
                Spacer(modifier = Modifier.height(40.dp))
                DeletePayBlock(
                    viewModel = viewModel,
                    list = deletePayList
                )
                Spacer(modifier = Modifier.height(32.dp))
                DeleteReasonBlock(
                    viewModel = viewModel,
                    list = deleteReasonList
                )
                Spacer(modifier = Modifier.height(36.dp))
                FeedBackBlock(
                    viewModel = viewModel,
                    feedBack = feedBack,
                    isError = isError
                )
                Spacer(modifier = Modifier.height(33.dp))
            }
        }
    }
}

