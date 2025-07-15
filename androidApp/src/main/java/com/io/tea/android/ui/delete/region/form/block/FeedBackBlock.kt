package com.io.tea.android.ui.delete.region.form.block

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.delete.region.form.DeleteRegionFormViewModel
import com.io.tea.android.ui.delete.region.form.MAX_LENGTH_FEED_BACK

@Composable
fun FeedBackBlock(
    viewModel: DeleteRegionFormViewModel,
    feedBack: String,
    isError: Boolean
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.delete_region_form__feed_back),
                color = Colors.FontSecondary,
                style = TeaAppTheme.typography.label,
                textAlign = TextAlign.Start
            )
            Text(
                text = "${feedBack.length}/255",
                color = Colors.FontSecondary,
                style = TeaAppTheme.typography.label,
                textAlign = TextAlign.End
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = feedBack,
            onValueChange = {
                if (it.length <= MAX_LENGTH_FEED_BACK) {
                    viewModel.onFeedBack(it)
                }
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.delete_region_form__place_holder),
                    color = Colors.Grey500,
                    style = TeaAppTheme.typography.h3,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            isError = isError,
        )
        if (isError) {
            Text(
                text = stringResource(id = R.string.delete_region_form__feed_back_input_error),
                color = Colors.Error,
                style = TeaAppTheme.typography.caption,
            )
        }
    }
}