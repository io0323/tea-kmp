package com.io.tea.android.ui.account.create

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.io.tea.android.R
import com.io.tea.android.resource.theme.TeaAppTheme
import com.io.tea.android.ui.common.PasswordField
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.common.block.PhoneFormBlock
import com.io.tea.android.ui.common.block.SmsNotReceivedLink
import com.io.tea.android.ui.common.component.CheckboxWithLink
import com.io.tea.android.ui.dialog.PickerDialog
import com.io.tea.android.ui.login.block.CautionBlock
import com.io.tea.android.ui.account.create.block.AccountCreateStepBlock
import com.io.tea.android.ui.account.create.enums.AccountCreateStep
import com.io.tea.android.ui.account.create.model.AccountCreateModel

@Composable
fun AccountCreateScreen(
    model: AccountCreateModel,
    onPopBack: () -> Unit = {},
    onClickSendSms: () -> Unit = {},
    onClickPicker: (Int) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onLinkClick: (String) -> Unit,
    onCheckedChangeTerms: (Boolean) -> Unit,
    onCheckedChangePrivacy: (Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val showPickerDialog = remember { mutableStateOf(false) }
    val countryNameList = model.countryCodeList.map {
        it.countryName
    }
    val countryCode =
        model.countryCodeList[model.currentCountryCodeIndex].let { "${it.countryName}${it.countryCode}" }
    val cautionList =
        model.phoneNumberCautionMsgList + model.passwordCautionMsgList

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.phone_number_input__header_title),
                onPopBack = onPopBack
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.phone_number_input__button_label,
                onClick = onClickSendSms,
                enabled = model.isEnable
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (showPickerDialog.value) {
                // TODO: データ取得
                PickerDialog(
                    listState = rememberLazyListState(model.currentCountryCodeIndex),
                    ogions = countryNameList,
                    onButtonClick = {
                        onClickPicker.invoke(it)
                        showPickerDialog.value = false
                    },
                    onDismissRequest = {
                        showPickerDialog.value = false
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AccountCreateStepBlock(AccountCreateStep.STEP1)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(R.string.phone_number_input__sms_label),
                style = TeaAppTheme.typography.h4,
            )
            Spacer(modifier = Modifier.height(14.dp))
            CautionBlock(
                list = cautionList
            )
            PhoneFormBlock(
                countryCode = countryCode,
                interactionSource = interactionSource,
                phoneNumber = model.phoneNumber ?: "",
                isError = model.phoneNumberCautionMsgList.isNotEmpty(),
                onCountryCodeFieldClick = {
                    showPickerDialog.value = true
                },
                onCountrySelected = {},
                onPhoneNumberChanged = onPhoneNumberChanged,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordField(
                interactionSource = interactionSource,
                password = model.password ?: "",
                isError = model.passwordCautionMsgList.isNotEmpty(),
                onValueChange = onValueChangePassword,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(R.string.phone_number_input__password_description),
                style = TeaAppTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            SmsNotReceivedLink(
                text = stringResource(R.string.phone_number_input__sms_not_received),
                linkedText = stringResource(R.string.common__password_forgot_link),
                link = model.smsLink,
                onLinkClick = {
                    onLinkClick(model.smsLink)
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            CheckboxWithLink(
                checked = model.isCheckedTerms,
                onCheckedChange = {
                    onCheckedChangeTerms(model.isCheckedTerms)
                },
                url = model.termsLink,
                linkedText = stringResource(R.string.phone_number_input__terms_link),
                onLinkClick = {
                    onLinkClick(model.termsLink)
                },
                suffix = stringResource(R.string.phone_number_input__link_suffix)
            )
            Spacer(modifier = Modifier.height(16.dp))
            CheckboxWithLink(
                checked = model.isCheckedPrivacy,
                onCheckedChange = {
                    onCheckedChangePrivacy(model.isCheckedPrivacy)
                },
                url = model.privacyLink,
                linkedText = stringResource(R.string.phone_number_input__privacy_link),
                onLinkClick = {
                    onLinkClick(model.privacyLink)
                },
                suffix = stringResource(R.string.phone_number_input__link_suffix)
            )
            Spacer(modifier = Modifier.height(21.dp))
        }
    }
}
