package com.io.tea.android.ui.region.list.blocks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme

@Composable
fun RegionListFilters(
    count: Int,
    isCheckBoxChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isFilterOn: Boolean,
    onFilterClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Colors.Base.regionListBackground)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        RegionListCount(
            count = count,
            modifier = Modifier.weight(1f)
        )
        RegionCheckBox(
            isChecked = isCheckBoxChecked,
            onCheckedChange = onCheckedChange
        )
        RegionFilter(
            isChecked = isFilterOn,
            onFilterClick = onFilterClick
        )
    }
}

@Composable
private fun RegionListCount(count: Int, modifier: Modifier = Modifier) {
    val annotatedText = buildAnnotatedString {
        append(
            AnnotatedString(
                text = "$count",
                spanStyle = SpanStyle(
                    fontSize = TeaAppTheme.typography.regionListCountLabel.fontSize,
                    fontWeight = TeaAppTheme.typography.regionListCountLabel.fontWeight
                )
            )
        )
        append(
            AnnotatedString(
                text = stringResource(R.string.region_list__count_unit),
                spanStyle = SpanStyle(
                    fontSize = TeaAppTheme.typography.regionListCountUnitLabel.fontSize,
                    fontWeight = TeaAppTheme.typography.regionListCountUnitLabel.fontWeight
                )
            )
        )
    }
    Text(
        text = annotatedText,
        color = Colors.Font.primary,
        modifier = modifier
    )
}

@Composable
private fun RegionCheckBox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .toggleable(
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onCheckedChange
            )
            .padding(
                vertical = 9.dp,
                horizontal = 16.dp
            )
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = null, // nullにしないと、Checkboxの横にスペースが入る
            modifier = Modifier.padding(horizontal = 2.dp)
        )
        Text(
            text = stringResource(R.string.region_list__checkbox_label),
            style = TeaAppTheme.typography.regionListCheckboxLabel,
        )
    }
}

@Composable
private fun RegionFilter(
    isChecked: Boolean,
    onFilterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onFilterClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.Base.regionListFilterButton,
            contentColor = Colors.Base.primary
        ),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 4.dp
        ),
        border = BorderStroke(1.dp, Colors.Base.tertiary),
        modifier = modifier.height(30.dp),
    ) {
        RegionFilterIcon(isChecked)
    }
}

@Composable
private fun RegionFilterIcon(isChecked: Boolean = false) {
    Box {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = stringResource(R.string.region_list__filter_label),
            modifier = Modifier.size(24.dp)
        )
        if (isChecked) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        Color.Red,
                        shape = MaterialTheme.shapes.small
                    )
                    .align(Alignment.TopEnd)
            )
        }
    }
    Text(
        text = stringResource(R.string.region_list__filter_label),
        style = TeaAppTheme.typography.regionListFilterLabel
    )
}
