package com.io.tea.android.ui.permission

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonPrimary
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun NotificationPermissionScreen(
    viewModel: NotificationPermissionViewModel = koinViewModel(),
) {
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonPrimary(
                    textResId = R.string.notification_permission__next,
                    isEnabled = true,
                    onClick = { viewModel.onClickNextButton() },
                    buttonModifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = "walk through",
                modifier = Modifier
                    .width(290.dp)
                    .height(363.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.notification_permission__image_one),
                style = TeaAppTheme.typography.h1,
                color = TeaAppTheme.colors.FontPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.notification_permission__image_one_description),
                style = TeaAppTheme.typography.h6,
                color = TeaAppTheme.colors.FontPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}
