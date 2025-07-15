package com.io.tea.android.ui.user.input.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.dialog.Picker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * ピッカーに表示される選択肢は、３つの想定です。
 */
@Composable
internal fun BirthPickerDialog(
    yearListState: LazyListState = rememberLazyListState(),
    monthListState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    yearOgions: List<String>,
    monthOgions: List<String>,
    onButtonClick: (Int, Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var lastYearScrollIndex by remember { mutableIntStateOf(-1) }
    var lastMonthScrollIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(yearListState) {
        snapshotFlow { yearListState.isScrollInProgress }
            .collect { isScrollInProgress ->
                if (!isScrollInProgress) {
                    val selectedIndex = yearListState.firstVisibleItemIndex
                    if (selectedIndex != lastYearScrollIndex) {
                        lastYearScrollIndex = selectedIndex
                        coroutineScope.launch {
                            yearListState.animateScrollToItem(selectedIndex)
                        }
                    }
                }
            }
    }

    LaunchedEffect(monthListState) {
        snapshotFlow { monthListState.isScrollInProgress }
            .collect { isScrollInProgress ->
                if (!isScrollInProgress) {
                    val selectedIndex = monthListState.firstVisibleItemIndex
                    if (selectedIndex != lastMonthScrollIndex) {
                        lastMonthScrollIndex = selectedIndex
                        coroutineScope.launch {
                            monthListState.animateScrollToItem(selectedIndex)
                        }
                    }
                }
            }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            val screenWidth = with(LocalDensity.current) {
                constraints.maxWidth.toDp()
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Picker(
                        ogions = yearOgions,
                        pickerHeight = 160.dp,
                        dividerWidth = screenWidth / 3,
                        listState = yearListState,
                        textStyle = TeaAppTheme.typography.value,
                        modifier = Modifier.weight(1f)
                    )
                    Picker(
                        ogions = monthOgions,
                        pickerHeight = 160.dp,
                        dividerWidth = screenWidth / 3,
                        listState = monthListState,
                        textStyle = TeaAppTheme.typography.value,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onButtonClick(
                            yearListState.firstVisibleItemIndex,
                            monthListState.firstVisibleItemIndex
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.phone_number_input__picker_button_label),
                        style = TeaAppTheme.typography.btnL
                    )
                }
            }
        }
    }
}
