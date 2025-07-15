package com.io.tea.android.ui.menu.model

import androidx.annotation.StringRes
import com.io.tea.android.ui.menu.model.PushSettingType

data class PushSettingTypeModel(
    @StringRes val subTextRes: Int,
    val pushSettingType: PushSettingType
)
