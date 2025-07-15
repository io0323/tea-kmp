package com.io.tea.android.ui.delete.region.confirm.block

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun CautionBlock(
    list: List<Int>,
) {
    Column {
        Text(
            text = stringResource(id = R.string.delete_region_confirm__caution_following),
            color = TeaAppTheme.colors.FontPrimary,
            style = TeaAppTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(1.dp, TeaAppTheme.colors.Key),
                    shape = RoundedCornerShape(4.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = "caution",
                        tint = TeaAppTheme.colors.FontSecondary,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.delete_region_confirm__caution_title),
                        color = TeaAppTheme.colors.FontSecondary,
                        style = TeaAppTheme.typography.label
                    )
                }
                Spacer(modifier = Modifier.height(4.5.dp))
                list.forEach {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = "・",
                            color = TeaAppTheme.colors.Error,
                            style = TeaAppTheme.typography.labelL,
                        )
                        Text(
                            text = stringResource(id = it),
                            color = TeaAppTheme.colors.Error,
                            style = TeaAppTheme.typography.labelL,
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}