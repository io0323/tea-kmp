package com.io.tea.android.ui.delete.region.form.block

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
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun GuidanceBlock() {
    Column {
        Text(
            text = stringResource(id = R.string.delete_region_form__guidance),
            color = Colors.FontPrimary,
            style = TeaAppTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Colors.Key), shape = RoundedCornerShape(4.dp))
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
                        tint = Colors.FontSecondary,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string.delete_region_form__caution_title),
                        color = Colors.FontSecondary,
                        style = TeaAppTheme.typography.label
                    )
                }
                Spacer(modifier = Modifier.height(4.5.dp))
                Text(
                    text = stringResource(id = R.string.delete_region_form__caution_text),
                    color = Colors.Error,
                    style = TeaAppTheme.typography.labelL,
                )
            }
        }
    }
}