package com.io.tea.android.ui.account.create.model

import androidx.annotation.StringRes
import com.io.tea.android.ui.common.component.model.CountryModel

data class AccountCreateModel(
    val countryCodeList: List<CountryModel>,
    val currentCountryCodeIndex: Int,
    val phoneNumber: String?,
    val password: String?,
    val isEnable: Boolean,
    val smsLink: String,
    val termsLink: String,
    val privacyLink: String,
    var isCheckedTerms: Boolean,
    var isCheckedPrivacy: Boolean,
    @StringRes
    val phoneNumberCautionMsgList: List<Int>,
    @StringRes
    val passwordCautionMsgList: List<Int>,
) {
    companion object {
        val default = AccountCreateModel(
            countryCodeList = emptyList(),
            currentCountryCodeIndex = 0,
            phoneNumber = null,
            password = null,
            isEnable = false,
            smsLink = "https://www.google.com",
            termsLink = "https://www.google.com",
            privacyLink = "https://www.google.com",
            phoneNumberCautionMsgList = emptyList(),
            passwordCautionMsgList = emptyList(),
            isCheckedTerms = false,
            isCheckedPrivacy = false,
        )
    }
}
