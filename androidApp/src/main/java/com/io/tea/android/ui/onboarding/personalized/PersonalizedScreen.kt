package com.io.tea.android.ui.onboarding.personalized

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.LocalWindowSize
import com.io.tea.android.ui.onboarding.personalized.block.PersonalizedKeywordBlock
import com.io.tea.android.ui.onboarding.personalized.model.PersonalizedFilterType
import com.io.tea.android.ui.onboarding.personalized.model.PersonalizedModel

@Composable
internal fun PersonalizedScreen(
    model: PersonalizedModel,
    onClickPersonalizedFilter: (PersonalizedFilterType) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val padding = if (LocalWindowSize.current.isTablet) 32.dp else 16.dp
    val listState = rememberLazyListState()

    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    painter = painterResource(id = model.indicator),
                    contentDescription = "indicator",
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            val boxModifier = if (screenHeight.value > 620) {
                Modifier.fillMaxWidth()
            } else {
                Modifier.height(screenHeight * 0.435f)
            }
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        item {
                            Text(
                                text = stringResource(model.summary),
                                style = TeaAppTheme.typography.h1,
                                color = TeaAppTheme.colors.FontPrimary,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(model.description),
                                style = TeaAppTheme.typography.h6,
                                color = TeaAppTheme.colors.FontPrimary,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            if (model.personalizedFilterList.isNotEmpty()) {
                                PersonalizedKeywordBlock(
                                    personalizedFilterList = model.personalizedFilterList,
                                    padding = padding,
                                    onClick = onClickPersonalizedFilter,
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                            }
                        }
                    }
                )
            }
        }
    }
}
