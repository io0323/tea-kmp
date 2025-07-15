package com.io.tea.android.ui.onboarding.walkthrough

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonPrimary
import com.io.tea.android.ui.onboarding.walkthrough.model.WalkThroughModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WalkThroughScreen(
    currentPage: Int,
    featureList: List<WalkThroughModel>,
    onPageChanged: (Int) -> Unit,
    onClickSkip: () -> Unit,
    onClickStart: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { featureList.size })
    val coroutineScope = rememberCoroutineScope()
    val model = featureList[currentPage]

    Scaffold(
        topBar = {
            if (currentPage == featureList.size) {
                CenterAlignedTopAppBar(
                    title = {},
                    actions = {}
                )
            } else {
                CenterAlignedTopAppBar(
                    title = {},
                    actions = {
                        TextButton(onClick = onClickSkip) {
                            Text(stringResource(R.string.common__skip))
                        }
                    }
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = model.indicator),
                    contentDescription = "indicator",
                )
                Spacer(modifier = Modifier.height(18.dp))
                ButtonPrimary(
                    textResId = model.buttonText,
                    isEnabled = true,
                    onClick = {
                        val page = currentPage + 1
                        if (page == featureList.size) {
                            onClickStart()
                        } else {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page = page)
                            }
                        }
                    },
                    buttonModifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState
        ) {
            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }.collect { page ->
                    onPageChanged(page)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = model.image),
                    contentDescription = "walkthrough image",
                    modifier = Modifier.size(290.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = model.summary),
                    style = TeaAppTheme.typography.h1,
                    color = TeaAppTheme.colors.FontPrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = model.description),
                    style = TeaAppTheme.typography.h6,
                    color = TeaAppTheme.colors.FontPrimary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
