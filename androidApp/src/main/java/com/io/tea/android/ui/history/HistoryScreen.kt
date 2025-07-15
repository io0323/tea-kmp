package com.io.tea.android.ui.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.nav.bottom.BottomBar
import com.io.tea.android.nav.bottom.OnClickBottomNavItem
import com.io.tea.android.ui.common.LoadIndicator
import com.io.tea.android.ui.common.PaySwitch
import com.io.tea.android.ui.common.component.model.TabButtonItem
import com.io.tea.android.ui.history.block.TransactionHistoryListBlock
import com.io.tea.android.ui.history.block.TransactionHistoryTotalPointBlock
import com.io.tea.android.ui.history.state.TransactionHistoryUseCaseState

@Composable
internal fun TransactionHistoryScreen(
    viewModel: HistoryViewModel,
    useCase: TransactionHistoryUseCaseState,
    tabList: List<TabButtonItem>,
    onClickBottomNavItem: OnClickBottomNavItem,
    totalPoint: String,
) {
    Scaffold(
        topBar = {},
        bottomBar = {
            BottomBar(onClickBottomNavItem = onClickBottomNavItem)
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (useCase) {
                is TransactionHistoryUseCaseState.Failure -> {}
                is TransactionHistoryUseCaseState.Initial -> {}
                is TransactionHistoryUseCaseState.Loading -> {
                    LoadIndicator(Modifier.fillMaxSize())
                }

                is TransactionHistoryUseCaseState.Success -> {
                    val modelList = useCase.data
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TransactionHistoryTotalPointBlock(
                            totalPoint = totalPoint,
                            viewModel = viewModel
                        )
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyColumn {
                                // NOTE : 1stリリース見送り TODO : SmallPhoneでボタンテキスト見切れる
//                                item {
//                                    TabButton(
//                                        tabList = tabList,
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(horizontal = 22.dp, vertical = 8.dp),
//                                        onClick = {
//                                            viewModel.onClickTab(it)
//                                        },
//                                    )
//                                }
                                item {
                                    TransactionHistoryListBlock(
                                        modelList = modelList,
                                    )
                                }
                            }
                            PaySwitch(
                                onClick = { viewModel.onClickPaySwitch() },
                                buttonModifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(bottom = 14.dp),
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }
            }
        }
    }
}
