package com.io.tea.android.ui.user.input.model

import com.io.tea.android.ui.user.model.Birth

data class UserInformationInputModel(
    val birth: Birth = Birth.default,
    val genders: List<String>,
    val isJapanCountryCode: Boolean
)
