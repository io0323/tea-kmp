package com.io.tea.android.ui.menu.model

import com.io.tea.android.R
import com.io.tea.android.ui.menu.model.PushSettingType

internal object PushSettingTypeModelConverter {
    fun convert(pushSettingType: PushSettingType): PushSettingTypeModel =
        when (pushSettingType) {
            PushSettingType.ON -> PushSettingTypeModel(
                subTextRes = R.string.drawer_menu__push_setting_on,
                pushSettingType = pushSettingType,
            )

            PushSettingType.OFF -> PushSettingTypeModel(
                subTextRes = R.string.drawer_menu__push_setting_off,
                pushSettingType = pushSettingType,
            )
        }
}