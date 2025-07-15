package com.io.tea.android.ui.login.model

import androidx.annotation.StringRes
import com.io.tea.android.ui.common.component.model.CountryModel
import com.io.tea.android.ui.login.state.LogInForm

data class LogInModel(
    val currentForm: LogInForm,
    val countryCodeList: List<CountryModel>,
    val currentCountryCodeIndex: Int,
    val phoneNumber: String?,
    val mailAddress: String?,
    val password: String?,
    val isEnable: Boolean,
    val passwordForgotLink: String,
    @StringRes
    val phoneNumberCautionMsgList: List<Int>,
    @StringRes
    val mailAddressCautionMsgList: List<Int>,
    @StringRes
    val passwordCautionMsgList: List<Int>,
) {
    companion object {
        val default = LogInModel(
            currentForm = LogInForm.PHONE,
            countryCodeList = emptyList(),
            currentCountryCodeIndex = 0,
            phoneNumber = null,
            mailAddress = null,
            password = null,
            isEnable = false,
            passwordForgotLink = "https://www.google.com",
            phoneNumberCautionMsgList = emptyList(),
            mailAddressCautionMsgList = emptyList(),
            passwordCautionMsgList = emptyList(),
        )
    }
}
