package com.io.tea.android.ui.user.model

data class UserInformationModel(
    val birth: String,
    val gender: String,
    val zip: String,
    val address: String,
    val isJapanCountryCode: Boolean
) {
    companion object {
        val empty: UserInformationModel
            get() = UserInformationModel(
                birth = "",
                gender = "",
                zip = "",
                address = "",
                isJapanCountryCode = true,
            )
    }
}
