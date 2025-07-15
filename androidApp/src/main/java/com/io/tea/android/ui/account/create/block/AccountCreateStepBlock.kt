package com.io.tea.android.ui.account.create.block

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.account.create.enums.AccountCreateStep

@Composable
fun AccountCreateStepBlock(step: AccountCreateStep) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(step.resourceId),
            contentDescription = "indicator",
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AccountCreateStep(stringResource(R.string.phone_number_input__indicator_first))
            Spacer(modifier = Modifier.width(24.dp))
            AccountCreateStep(stringResource(R.string.phone_number_input__indicator_second))
            Spacer(modifier = Modifier.width(24.dp))
            AccountCreateStep(stringResource(R.string.phone_number_input__indicator_third))
        }
    }
}

@Composable
fun AccountCreateStep(text: String) {
    Text(
        text = text,
        style = TeaAppTheme.typography.caption,
        color = Colors.Font.second,
        textAlign = TextAlign.Center,
        modifier = Modifier.width(87.dp),
    )
}
