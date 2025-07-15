package com.io.tea.android.ui.delete.account.auth

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.io.tea.android.ui.common.block.PhoneFormCountryCodeTextBlock
import com.io.tea.android.ui.delete.account.auth.block.CautionBlock
import com.io.tea.android.ui.delete.account.auth.state.DeleteAccountAuthState
import com.io.tea.android.ui.delete.account.auth.state.DeleteAccountAuthType
import com.io.tea.android.ui.dialog.PickerDialog

@Composable
internal fun DeleteAccountAuthScreen(
    countryCode: String,
    deleteAccountAuth: DeleteAccountAuthState,
    errorList: List<Int>,
    onPopBack: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickLogin: () -> Unit = {},
) {
    // TODO: ViewModelへ移行
    val authState = remember { mutableStateOf(DeleteAccountAuthState(deleteAccountAuth.authType)) }
    val interactionSource = remember { MutableInteractionSource() }
    val showPickerDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(R.string.delete_account_auth__header_title),
                onPopBack = onPopBack,
            )
        },
        bottomBar = {
            BottomBar(
                resourceIdLeft = R.string.common__cancel,
                resourceIdRight = R.string.common_login,
                onClickLeft = onClickCancel,
                onClickRight = onClickLogin,
                isLeftButtonEnabled = true,
                isRightButtonEnabled = true,
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.delete_account_auth___title),
                style = TeaAppTheme.typography.h1,
                color = TeaAppTheme.colors.FontPrimary,
            )
            Spacer(modifier = Modifier.height(15.dp))
            SegmentedButton(
                leftLabel = stringResource(R.string.login__phone),
                rightLabel = stringResource(R.string.login__email),
                isSelectedLeft = authState.value.authType == DeleteAccountAuthType.PHONE,
                onClickLeft = {
                    authState.value = DeleteAccountAuthState(DeleteAccountAuthType.PHONE)
                },
                onClickRight = {
                    authState.value = DeleteAccountAuthState(DeleteAccountAuthType.EMAIL)
                }
            )
            Spacer(modifier = Modifier.height(13.dp))
            // TODO : Mock AuthType指定不要
            if (errorList.isNotEmpty() && authState.value.authType == DeleteAccountAuthType.EMAIL) {
                CautionBlock(errorList)
                Spacer(modifier = Modifier.height(24.dp))
            }
            when (authState.value.authType) {
                DeleteAccountAuthType.PHONE -> {
                    // TODO: ログインの種別ボタンを切替後も電話番号は保持する
                    PhoneFormCountryCodeTextBlock(
                        countryCode = countryCode,
                        interactionSource = interactionSource,
                        onPhoneNumberChanged = {},
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 8.dp)
                    )
                }

                DeleteAccountAuthType.EMAIL -> {
                    // TODO: ログインの種別ボタンを切替後もメールアドレスは保持する
                    EmailFormBlock(
                        defaultText = "",
                        placeholder = {
                            Text(
                                text = stringResource(R.string.login__email_form_hint),
                                style = TeaAppTheme.typography.h4
                            )
                        },
                        interactionSource = interactionSource,
                        onValueChange = {},
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 8.dp)
                    )
                }
            }
            PasswordBlock(
                interactionSource,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        if (showPickerDialog.value) {
            // TODO: データ取得
            PickerDialog(
                ogions = listOf(
                    "日本",
                    "中国",
                    "香港",
                    "韓国",
                    "台湾",
                    "オーストラリア",
                    "カナダ",
                    "イギリス",
                    "アメリカ",
                ),
                onButtonClick = {},
                onDismissRequest = {
                    showPickerDialog.value = false
                }
            )
        }
    }
}
