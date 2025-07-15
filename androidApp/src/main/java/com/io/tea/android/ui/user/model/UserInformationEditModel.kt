package com.io.tea.android.ui.user.model

import com.io.tea.android.ui.user.model.Birth

data class UserInformationEditModel(
    val birth: Birth,
    val gender: String,
    val zip: String,
    val address: String,
    val isJapanCountryCode: Boolean,
) {
    companion object {
        val empty: UserInformationEditModel
            get() = UserInformationEditModel(
                birth = Birth.default,
                gender = "",
                zip = "",
                address = "",
                isJapanCountryCode = true,
            )
    }
}
