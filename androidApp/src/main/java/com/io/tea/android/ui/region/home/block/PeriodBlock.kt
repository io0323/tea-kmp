package com.io.tea.android.ui.region.home.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.component.ButtonQuaternary
import com.io.tea.android.ui.common.component.model.ButtonIconType
import com.io.tea.android.ui.region.home.model.PeriodModel

@Composable
internal fun PeriodBlock(
    periodModel: PeriodModel,
    onDetailClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(periodModel.regionColor)
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp, bottom = 13.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = periodModel.couponName,
            style = TeaAppTheme.typography.h2,
            color = TeaAppTheme.colors.FontContrast,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.region_home__period),
                style = TeaAppTheme.typography.h6,
                color = TeaAppTheme.colors.FontContrast,
            )
            Text(
                text = periodModel.period,
                style = TeaAppTheme.typography.h6,
                color = TeaAppTheme.colors.FontContrast,
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonQuaternary(
                isEnabled = true,
                textResId = R.string.region_home__period_detail,
                textStyle = TeaAppTheme.typography.btn,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                iconType = ButtonIconType.RIGHT,
                onClick = onDetailClick
            )
        }
    }
}