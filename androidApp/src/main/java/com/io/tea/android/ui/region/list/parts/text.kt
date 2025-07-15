package com.io.tea.android.ui.region.list.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun PrefectureText(prefecture: String) {
    Text(
        text = prefecture,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(start = 10.dp, top = 5.dp, end = 0.dp, bottom = 5.dp)
    )
}