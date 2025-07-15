package com.io.tea.android.ui.notice.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.io.tea.android.ui.notice.detail.model.NoticeDetailModel
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.LoadIndicator
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.notice.common.NoticeItem
import com.io.tea.android.util.TextUtil.LinkText
import org.koin.androidx.compose.koinViewModel

@Composable
fun NoticeDetailScreen(
    viewModel: NoticeDetailViewModel = koinViewModel(),
    noticeDetail: NoticeDetailModel?,
    isLoad: Boolean,
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.screen__notice_details),
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
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .verticalScroll(scrollState)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box {
                                noticeDetail?.let {
                                    NoticeItem(
                                        notice = it.noticeModel,
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Column(
                            modifier = Modifier.padding(bottom = 10.dp)
                        ) {
                            noticeDetail?.let {
                                LinkText(
                                    text = it.message,
                                    linkedText = "こちら",
                                    link = it.noticeModel.url,
                                    textFontStyle = TeaAppTheme.typography.h5,
                                    linkFontStyle = TeaAppTheme.typography.h5,
                                    onClick = { viewModel.onClickLink(it.noticeModel.url) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


