package com.io.tea.android.ui.region.list.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.io.tea.android.ui.common.state.ButtonState

@Composable
internal fun PrefectureButton() {
    OutlinedButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(10)
    ) {
        Text(text = "都道府県", fontSize = 12.sp)
    }
}

@Composable
internal fun DeleteButton() {
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Lotの退会",
            fontSize = 12.sp
        )
    }
}

@Composable
internal fun NoAddedButton(onClick: () -> Unit, noAddButtonState: ButtonState) {
    OutlinedButton(
        modifier = Modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.buttonColors(
            containerColor = when (noAddButtonState) {
                is ButtonState.Active -> Color(0xFF6200EE)
                is ButtonState.Inactive -> Color.White
            }
        )
    ) {
        Text(
            text = "未追加",
            fontSize = 12.sp,
            color = when (noAddButtonState) {
                ButtonState.Active -> Color.White
                ButtonState.Inactive -> Color(0xFF6200EE)
            }
        )
    }
}
