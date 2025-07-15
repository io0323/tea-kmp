package com.io.tea.android.ui.login

import androidx.lifecycle.ViewModel
import com.io.tea.android.ui.login.model.LogInModel
import com.io.tea.android.ui.login.state.LogInForm
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.common.component.model.CountryModel
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.util.ValidationUtil.isValidationMailAddress
import com.io.tea.android.util.ValidationUtil.isValidationPasswordContainsRequired
import com.io.tea.android.util.ValidationUtil.isValidationPhoneNumber
import com.io.tea.android.util.ValidationUtil.isValidationStringCount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LogInViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _loginModelStateFlow = MutableStateFlow(LogInModel.default)
    val loginModelStateFlow: StateFlow<LogInModel> = _loginModelStateFlow

    init {
        fetchCountryCode()
    }

    private fun fetchCountryCode() {
        val countryNameList = listOf(
            "日本",
            "中国",
            "香港",
            "韓国",
            "台湾",
            "オーストラリア",
            "カナダ",
            "イギリス",
            "アメリカ",
        )
        val countryNumberList = listOf(
            " (+81)",
            " (+86)",
            " (+852)",
            " (+82)",
            " (+886)",
            " (+61)",
            " (+1)",
            " (+44)",
            " (+1)",
        )
        val countryCodeList = countryNameList.indices.map { index ->
            CountryModel(index, countryNameList[index], countryNumberList[index])
        }
        _loginModelStateFlow.update {
            it.copy(
                countryCodeList = countryCodeList,
            )
        }
    }

    private fun checkValidation() {
        // TODO : Validation
        val loginForm = _loginModelStateFlow.value.currentForm
        val isEnable = when (loginForm) {
            LogInForm.PHONE -> {
                val phoneNumber = _loginModelStateFlow.value.phoneNumber
                val password = _loginModelStateFlow.value.password
                val isError =
                    (_loginModelStateFlow.value.phoneNumberCautionMsgList + _loginModelStateFlow.value.passwordCautionMsgList).isNotEmpty()
                when {
                    phoneNumber?.isNotEmpty() == true && password?.isNotEmpty() == true && isError.not() -> true
                    else -> false
                }
            }

            LogInForm.EMAIL -> {
                val mailAddress = _loginModelStateFlow.value.mailAddress
                val password = _loginModelStateFlow.value.password
                val isError =
                    (_loginModelStateFlow.value.mailAddressCautionMsgList + _loginModelStateFlow.value.passwordCautionMsgList).isNotEmpty()
                when {
                    mailAddress?.isNotEmpty() == true && password?.isNotEmpty() == true && isError.not() -> true
                    else -> false
                }
            }
        }
        _loginModelStateFlow.update {
            it.copy(
                isEnable = isEnable,
            )
        }
    }

    fun onClickForm(loginForm: LogInForm) {
        _loginModelStateFlow.update {
            it.copy(
                currentForm = loginForm,
            )
        }
        checkValidation()
    }

    fun onClickPicker(index: Int) {
        _loginModelStateFlow.update {
            it.copy(
                currentCountryCodeIndex = index,
            )
        }
    }

    fun onClickLogIn() {
        val isEnable = _loginModelStateFlow.value.isEnable
        if (isEnable) {
            _navigationStateFlow.value = HomeDestination("")
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onPhoneNumberChanged(value: String) {
        val errorMsgList: MutableList<Int> = mutableListOf()
        if (isValidationPhoneNumber(value).not()) {
            // TODO : ErrorMsg
        }
        if (isValidationStringCount(value, 8).not()) {
            errorMsgList.add(R.string.login__error_string_count)
        }
        _loginModelStateFlow.update {
            it.copy(
                phoneNumber = value,
                phoneNumberCautionMsgList = errorMsgList
            )
        }
        checkValidation()
    }

    fun onValueChangeMailAddress(value: String) {
        val errorMsgList: MutableList<Int> = mutableListOf()
        if (isValidationMailAddress(value).not()) {
            // TODO : ErrorMsg
        }
        _loginModelStateFlow.update {
            it.copy(
                mailAddress = value,
                mailAddressCautionMsgList = errorMsgList
            )
        }
        checkValidation()
    }

    fun onValueChangePassword(value: String) {
        val errorMsgList: MutableList<Int> = mutableListOf()
        if (isValidationStringCount(value, 8).not()) {
            errorMsgList.add(R.string.login__error_string_count)
        }
        if (isValidationPasswordContainsRequired(value).not()) {
            errorMsgList.add(R.string.login__error_password_contains_required)
        }
        _loginModelStateFlow.update {
            it.copy(
                password = value,
                passwordCautionMsgList = errorMsgList
            )
        }
        checkValidation()
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
