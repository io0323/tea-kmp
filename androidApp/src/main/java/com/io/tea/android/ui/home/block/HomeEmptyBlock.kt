package com.io.tea.android.ui.home.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun HomeEmptyBlockOne(
    onClickSearch: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                ),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "description",
                modifier = Modifier
                    .width(102.dp)
                    .height(86.dp)
            )
            Spacer(modifier = Modifier.height(17.8.dp))
            Text(
                text = stringResource(R.string.home__empty_info_text),
                textAlign = TextAlign.Center,
                style = TeaAppTheme.typography.h2,
                color = TeaAppTheme.colors.FontSecondary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.home__empty_state_appeal_text_one),
                textAlign = TextAlign.Center,
                style = TeaAppTheme.typography.h4,
                color = TeaAppTheme.colors.Blue
            )
            Spacer(modifier = Modifier.height(8.dp))
            HomeSearchBlock(
                onClickSearch = onClickSearch
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun HomeEmptyBlockTwo(
    onClickTeaAppSearch: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(
                    start = 40.dp,
                    end = 40.dp,
                ),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "description",
                modifier = Modifier
                    .width(102.dp)
                    .height(86.dp)
            )
            Spacer(modifier = Modifier.height(17.8.dp))
            Text(
                text = stringResource(R.string.home__empty_info_text),
                textAlign = TextAlign.Center,
                style = TeaAppTheme.typography.h2,
                color = TeaAppTheme.colors.FontSecondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.home__empty_state_appeal_text_two),
                textAlign = TextAlign.Center,
                style = TeaAppTheme.typography.h6,
                color = TeaAppTheme.colors.FontSecondary
            )
            Spacer(modifier = Modifier.height(51.dp))
            HomeSearchBlock(
                onClickSearch = onClickTeaAppSearch
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
