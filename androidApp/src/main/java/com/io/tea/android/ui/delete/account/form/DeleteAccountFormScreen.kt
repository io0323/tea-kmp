package com.io.tea.android.ui.delete.account.form

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
import com.io.tea.android.ui.delete.account.form.block.DeleteReasonBlock
import com.io.tea.android.ui.delete.account.form.block.FeedBackBlock
import com.io.tea.android.ui.delete.account.form.block.GuidanceBlock
import com.io.tea.android.R
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.delete.account.form.model.DeleteAccountItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeleteAccountFormScreen(
    viewModel: DeleteAccountFormViewModel = koinViewModel(),
    deleteReason: List<DeleteAccountItem>,
    feedBack: String,
    isError: Boolean,
    isConfirmButtonEnabled: Boolean,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.delete_account_form__header_title),
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
                DeleteReasonBlock(
                    viewModel = viewModel,
                    list = deleteReason
                )
                Spacer(modifier = Modifier.height(40.dp))
                FeedBackBlock(
                    viewModel = viewModel,
                    feedBack = feedBack,
                    isError = isError
                )
                // NOTE : Lot削除①削除フォームと合わせる
                Spacer(modifier = Modifier.height(33.dp))
            }
        }
    }
}

