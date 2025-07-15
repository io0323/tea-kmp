package com.io.tea.android.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.io.tea.android.R
import com.io.tea.android.resource.Colors
import com.io.tea.android.resource.theme.TeaAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * ピッカーに表示される選択肢は、３つの想定です。
 */
@Composable
internal fun PickerDialog(
    listState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    ogions: List<String>,
    onButtonClick: (Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var lastScrollIndex by remember { mutableIntStateOf(-1) }
    // 一番最初の要素と一番最後の要素を選択できるようにするために、選択肢の前後に空白を入れる。
    val customOgions = listOf("") + ogions + listOf("")

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .collect { isScrollInProgress ->
                if (!isScrollInProgress) {
                    val selectedIndex = listState.firstVisibleItemIndex
                    if (selectedIndex != lastScrollIndex) {
                        lastScrollIndex = selectedIndex
                        coroutineScope.launch {
                            listState.animateScrollToItem(selectedIndex)
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
                Picker(
                    ogions = customOgions,
                    pickerHeight = 160.dp,
                    dividerWidth = screenWidth / 3,
                    listState = listState,
                    textStyle = TeaAppTheme.typography.value
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onButtonClick(listState.firstVisibleItemIndex)
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


@Composable
internal fun Picker(
    listState: LazyListState,
    ogions: List<String>,
    pickerHeight: Dp,
    modifier: Modifier = Modifier,
    numberOfLines: Int = 3,
    dividerWidth: Dp,
    dividerColor: Color = Colors.Blue,
    dividerThickness: Dp = DividerDefaults.Thickness,
    textStyle: TextStyle = TeaAppTheme.typography.subtitle1.copy(
        textAlign = TextAlign.Center
    )
) {
    val rowHeight = pickerHeight / numberOfLines
    val firstDividerPosition = pickerHeight / numberOfLines
    val secondDividerPosition = (pickerHeight / numberOfLines) * 2

    Box(
        modifier = modifier
            .height(pickerHeight)
    ) {
        LazyColumn(
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(ogions.size) { index ->
                val alpha by remember {
                    derivedStateOf {
                        when (index) {
                            0, ogions.lastIndex -> 0f
                            listState.firstVisibleItemIndex + 1 -> 1f
                            else -> 0.3f
                        }
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(rowHeight)
                ) {
                    Text(
                        text = ogions[index],
                        style = textStyle,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.alpha(alpha)
                    )
                }
            }
        }

        HorizontalDivider(
            color = dividerColor,
            thickness = dividerThickness,
            modifier = Modifier
                .width(dividerWidth)
                .align(Alignment.TopCenter)
                .padding(top = firstDividerPosition)
        )

        HorizontalDivider(
            color = dividerColor,
            thickness = dividerThickness,
            modifier = Modifier
                .width(dividerWidth)
                .align(Alignment.TopCenter)
                .padding(top = secondDividerPosition)
        )
    }
}
