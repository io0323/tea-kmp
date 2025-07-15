package com.io.tea.android.ui.onboarding.personalized.block

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.LocalWindowSize
import com.io.tea.android.ui.onboarding.personalized.model.PersonalizedFilterType

@Composable
fun PersonalizedKeywordBlock(
    personalizedFilterList: List<PersonalizedFilterType>,
    padding: Dp,
    onClick: (PersonalizedFilterType) -> Unit,
) {
    val columSize = if (LocalWindowSize.current.isTablet) 1 else 2
    Spacer(modifier = Modifier.height(8.dp))
    val list = personalizedFilterList.withIndex().groupBy { it.index % columSize == 0 }.values
//    Log.d("", "★★★" + personalizedFilterList.size + " / " + list)

    LazyRow(
        contentPadding = PaddingValues(start = padding),
        userScrollEnabled = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                list.forEach {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        it.forEach {
                            Log.d("", "★★★" + it.value.title)
                            Button(
                                elevation = null,
                                onClick = { onClick(it.value) },
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = TeaAppTheme.colors.Blue.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(20.dp),
                                    )
                                    .height(37.dp),
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = TeaAppTheme.colors.White90
                                ),
                            ) {
                                Text(
                                    text = it.value.title,
                                    style = TeaAppTheme.typography.body1,
                                    color = TeaAppTheme.colors.Blue,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
