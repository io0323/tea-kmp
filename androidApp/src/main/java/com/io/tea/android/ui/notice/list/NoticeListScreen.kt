package com.io.tea.android.ui.notice.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.ui.common.LoadIndicator
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.notice.common.NoticeModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoticeListScreen(
    viewModel: NoticeListViewModel = koinViewModel(),
    noticeList: List<NoticeModel>,
    isLoad: Boolean,
) {
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.screen__notice_list),
                onPopBack = { viewModel.onPopBack() },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            when {
                isLoad -> {
                    LoadIndicator(Modifier.fillMaxSize())
                }

                else -> {
                    Box(
                        modifier = Modifier.padding(horizontal = 24.dp)
                    ) {
                        LazyColumn(
                            state = lazyListState,
                        ) {
                            items(noticeList) {
                                NoticeListItem(
                                    notice = it,
                                    onClick = {
                                        viewModel.onClickNoticeItem(it)
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
