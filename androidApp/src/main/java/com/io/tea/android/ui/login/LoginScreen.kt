package com.io.tea.android.ui.login

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
import com.io.tea.android.ui.common.BottomBar
import com.io.tea.android.ui.common.TopBar
import com.io.tea.android.ui.common.SegmentedButton
import com.io.tea.android.ui.common.block.EmailFormBlock
import com.io.tea.android.ui.common.block.PasswordBlock
import com.io.tea.android.ui.common.block.PhoneFormBlock
import com.io.tea.android.ui.dialog.PickerDialog
import com.io.tea.android.ui.login.block.CautionBlock
import com.io.tea.android.ui.login.block.descriptionBlock
import com.io.tea.android.ui.login.model.LogInModel
import com.io.tea.android.ui.login.state.LogInForm
import com.io.tea.android.ui.login.state.LogInState

@Composable
internal fun LogInScreen(
    model: LogInModel,
    onPopBack: () -> Unit = {},
    onClickForm: (LogInForm) -> Unit,
    onClickLogIn: () -> Unit = {},
    onClickPicker: (Int) -> Unit,
    onPhoneNumberChanged: (String) -> Unit,
    onValueChangeMailAddress: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
) {
    // TODO: ViewModelへ移行
    val interactionSource = remember { MutableInteractionSource() }
    val showPickerDialog = remember { mutableStateOf(false) }

    val countryNameList = model.countryCodeList.map {
        it.countryName
    }
    val countryCode =
        model.countryCodeList[model.currentCountryCodeIndex].let { "${it.countryName}${it.countryCode}" }

    val cautionList =
        model.phoneNumberCautionMsgList + model.mailAddressCautionMsgList + model.passwordCautionMsgList

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.login__header_title),
                onPopBack = onPopBack,
            )
        },
        bottomBar = {
            BottomBar(
                resourceId = R.string.common_login,
                enabled = model.isEnable,
                onClick = onClickLogIn
            )
        },
    ) { innerPadding ->
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            descriptionBlock(
                loginForm = LogInState(model.currentForm).form,
                modifier = Modifier.align(Alignment.Start)
            )

            SegmentedButton(
                leftLabel = stringResource(R.string.login__phone),
                rightLabel = stringResource(R.string.login__email),
                isSelectedLeft = model.currentForm == LogInForm.PHONE,
                onClickLeft = {
                    onClickForm(LogInForm.PHONE)
                },
                onClickRight = {
                    onClickForm(LogInForm.EMAIL)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CautionBlock(
                list = cautionList
            )
            when (model.currentForm) {
                LogInForm.PHONE -> {
                    // TODO: ログインの種別ボタンを切替後も電話番号は保持する
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
                }

                LogInForm.EMAIL -> {
                    // TODO: ログインの種別ボタンを切替後もメールアドレスは保持する
                    EmailFormBlock(
                        defaultText = model.mailAddress ?: "",
                        isError = model.mailAddressCautionMsgList.isNotEmpty(),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.login__email_form_hint),
                                style = TeaAppTheme.typography.h4
                            )
                        },
                        interactionSource = interactionSource,
                        onValueChange = onValueChangeMailAddress,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 8.dp)
                    )
                }
            }

            PasswordBlock(
                interactionSource,
                password = model.password ?: "",
                passwordForgotLink = model.passwordForgotLink,
                isError = model.passwordCautionMsgList.isNotEmpty(),
                onValueChange = onValueChangePassword,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}
