package com.io.tea.android.ui.user.block

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.ui.common.TeaAppTextFieldLabel

@Composable
internal fun UserAddressBlock(address: String) {
    TeaAppTextFieldLabel(
        resourceId = R.string.user_information_input__address,
        isRequired = false,
        modifierRow = Modifier.padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.height(4.dp))
    UserInformation(value = address)
}
