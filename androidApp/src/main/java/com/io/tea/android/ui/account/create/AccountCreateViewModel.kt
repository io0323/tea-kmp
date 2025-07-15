package com.io.tea.android.ui.account.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.io.tea.android.R
import com.io.tea.android.nav.Destination
import com.io.tea.android.nav.PopBackDestination
import com.io.tea.android.ui.account.auth.sms.AuthSmsDestination
import com.io.tea.android.ui.account.create.model.AccountCreateModel
import com.io.tea.android.ui.account.create.state.AccountCreateUiState
import com.io.tea.android.ui.common.component.model.CountryModel
import com.io.tea.android.ui.common.component.model.converter.CountryConverter
import com.io.tea.android.ui.webview.WebViewDestination
import com.io.tea.android.util.ValidationUtil
import com.io.tea.domain.usecase.FetchCountryListUseCase
import com.io.tea.domain.usecase.UseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class AccountCreateViewModel(
    private val fetchCountryListUseCase: FetchCountryListUseCase,
) : ViewModel(), KoinComponent {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    private val _accountCreateModelStateFlow = MutableStateFlow(AccountCreateModel.default)
    val accountCreateModelStateFlow = _accountCreateModelStateFlow.asStateFlow()

    private val _uiStateFlow = MutableStateFlow(AccountCreateUiState())
    internal val uiStateFlow = _uiStateFlow.asStateFlow()

    private val countryListStateFlow: MutableStateFlow<List<CountryModel>> =
        MutableStateFlow(emptyList())

    init {
        fetchCountryList()
    }

    private fun fetchCountryList() {
        viewModelScope.launch {
            when (val result = fetchCountryListUseCase()) {
                is UseCaseResult.Success -> {
                    countryListStateFlow.value = result.data.map {
                        CountryConverter.convert(it)
                    }
                    updateModel()
                    updateUiState(AccountCreateUiState.State.SUCCESS)
                }

                is UseCaseResult.Failure -> {
                    updateUiState(AccountCreateUiState.State.ERROR)
                }
            }
        }
    }

    private fun updateModel() {
        _accountCreateModelStateFlow.update {
            it.copy(
                countryCodeList = countryListStateFlow.value,
            )
        }
    }

    private fun updateUiState(state: AccountCreateUiState.State) {
        _uiStateFlow.update {
            it.copy(
                state = state
            )
        }
    }

    private fun checkValidation() {
        // TODO : Validation
        val phoneNumber = _accountCreateModelStateFlow.value.phoneNumber
        val password = _accountCreateModelStateFlow.value.password
        val isCheckedTerms = _accountCreateModelStateFlow.value.isCheckedTerms
        val isCheckedPrivacy = _accountCreateModelStateFlow.value.isCheckedPrivacy
        val isError =
            (_accountCreateModelStateFlow.value.phoneNumberCautionMsgList + _accountCreateModelStateFlow.value.passwordCautionMsgList).isNotEmpty()
        val isEnable = when {
            phoneNumber?.isNotEmpty() == true && password?.isNotEmpty() == true && isError.not() -> true && isCheckedTerms && isCheckedPrivacy
            else -> false
        }
        _accountCreateModelStateFlow.update {
            it.copy(
                isEnable = isEnable,
            )
        }
    }

    fun onPopBack() {
        _navigationStateFlow.value = PopBackDestination
    }

    fun onClickSendSms() {
        _navigationStateFlow.value = AuthSmsDestination()
    }

    fun onClickPicker(index: Int) {
        _accountCreateModelStateFlow.update {
            it.copy(
                currentCountryCodeIndex = index,
            )
        }
    }

    fun onPhoneNumberChanged(value: String) {
        val errorMsgList: MutableList<Int> = mutableListOf()
        if (ValidationUtil.isValidationPhoneNumber(value).not()) {
            // TODO : ErrorMsg
        }
        if (ValidationUtil.isValidationStringCount(value, 8).not()) {
            errorMsgList.add(R.string.phone_number_input__error_string_count)
        }
        _accountCreateModelStateFlow.update {
            it.copy(
                phoneNumber = value,
                phoneNumberCautionMsgList = errorMsgList
            )
        }
        checkValidation()
    }

    fun onValueChangePassword(value: String) {
        val errorMsgList: MutableList<Int> = mutableListOf()
        if (ValidationUtil.isValidationStringCount(value, 8).not()) {
            errorMsgList.add(R.string.phone_number_input__error_string_count)
        }
        if (ValidationUtil.isValidationPasswordContainsRequired(value).not()) {
            errorMsgList.add(R.string.phone_number_input__error_password_contains_required)
        }
        _accountCreateModelStateFlow.update {
            it.copy(
                password = value,
                passwordCautionMsgList = errorMsgList
            )
        }
        checkValidation()
    }

    fun onLinkClick(url: String) {
        _navigationStateFlow.value = WebViewDestination(url)
    }

    fun onCheckedChangeTerms(value: Boolean) {
        _accountCreateModelStateFlow.update {
            it.copy(
                isCheckedTerms = value.not(),
            )
        }
        checkValidation()
    }

    fun onCheckedChangePrivacy(value: Boolean) {
        _accountCreateModelStateFlow.update {
            it.copy(
                isCheckedPrivacy = value.not(),
            )
        }
        checkValidation()
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}
