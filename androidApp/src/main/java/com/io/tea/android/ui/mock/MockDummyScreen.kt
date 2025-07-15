package com.io.tea.android.ui.mock

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.io.tea.android.ui.common.state.SmsSource
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MockDummyScreen(
    viewModel: MockDummyViewModel = koinViewModel(),
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "MockDummy") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "contentDescription"
                    )
                }
            )
        },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            Button(onClick = { viewModel.onClickWalkThroughUsage() }) {
                Text("ウォークスルー サービス説明画面に遷移します")
            }
            Button(onClick = { viewModel.onClickPersonalized() }) {
                Text("パーソナライズ画面に遷移します画面に遷移します")
            }
            Button(onClick = { viewModel.onClickNotificationPermission() }) {
                Text("プッシュ通知要求画面に遷移します")
            }
            Button(onClick = { viewModel.onClickPhoneNumberInput() }) {
                Text("アカウント作成画面に遷移します")
            }
            Button(onClick = { viewModel.onClickUserInformation() }) {
                Text("ユーザー情報画面に遷移します")
            }
            Button(onClick = { viewModel.onClickUserInformationEdit() }) {
                Text("ユーザー情報編集画面に遷移します")
            }
            Button(onClick = { viewModel.onClickSmsCodeInput() }) {
                Text("SMS認証(新規登録)画面に遷移します")
            }
            Button(onClick = { viewModel.onClickHome("1") }) {
                Text("ホーム画面に遷移します")
            }
            Button(onClick = { viewModel.onClickUserInformationInput() }) {
                Text("新規会員登録画面に遷移します")
            }
            Button(onClick = { viewModel.onClickLOptIn() }) {
                Text("ログイン画面に遷移します")
            }
            Button(onClick = { viewModel.onClickLogInSms(SmsSource.PHONE) }) {
                Text("SMS認証(ログイン)画面に遷移します")
            }
            Button(onClick = { viewModel.onClickPasscodeSetting("1") }) {
                Text("パスコード設定画面に遷移します")
            }
            Button(onClick = { viewModel.onClickMyTeaTop("1") }) {
                Text("MyTeaTop画面に遷移します")
            }
            Button(onClick = { viewModel.onClickRegionList("1") }) {
                Text("Lotリスト画面に遷移します")
            }
            Button(onClick = { viewModel.onClickRegionDetail("1") }) {
                Text("Lot詳細画面に遷移します")
            }
            Button(onClick = { viewModel.onClickMyRegionList("1") }) {
                Text("MYLot一覧画面に遷移します")
            }
            Button(onClick = { viewModel.onClickTransactionHistory() }) {
                Text("取引履歴＆Lot有効期限画面に遷移します")
            }
            Button(onClick = { viewModel.onClickCodeInput() }) {
                Text("指定Lot入力画面に遷移します")
            }
            Button(onClick = { viewModel.onClickPaymentQrScan() }) {
                Text("決済QRカメラ画面に遷移します")
            }
            Button(onClick = { viewModel.onClickChargeQrScan() }) {
                Text("LotQRカメラ画面に遷移します")
            }
            Button(onClick = { viewModel.onClickChargeCodeInput() }) {
                Text("Lotコード入力画面に遷移します")
            }
            Button(onClick = { viewModel.onClickChargeCodeConfirm() }) {
                Text("Lot確認画面に遷移します")
            }
            Button(onClick = { viewModel.onClickChargeComplete() }) {
                Text("Lot完了画面に遷移します")
            }
            Button(onClick = { viewModel.onClickPaymentPointInput() }) {
                Text("決済Lot入力画面に遷移します")
            }
            Button(onClick = { viewModel.onClickPaymentPointConfirm() }) {
                Text("決済Lot確認画面に遷移します")
            }
            Button(onClick = { viewModel.onClickPaymentComplete() }) {
                Text("決済完了画面に遷移します")
            }
            Button(onClick = { viewModel.onClickNoticeList("1") }) {
                Text("お知らせ一覧画面に遷移します")
            }
            Button(onClick = { viewModel.onClickDrawerMenu("1") }) {
                Text("グローバルメニュー画面に遷移します")
            }
            Button(onClick = { viewModel.onClickDeleteRegionForm("1") }) {
                Text("Lot削除① 削除フォーム画面に遷移します")
            }
            Button(onClick = { viewModel.onClickDeleteRegionConfirm("1") }) {
                Text("Lot削除② 削除確認画面に遷移します")
            }
//            Button(onClick = { viewModel.onClickDeleteAccountAuthForm(SmsSource.PHONE) }) {
//                Text("退会 アカウント認証画面に遷移します")
//            }
            Button(onClick = { viewModel.onClickDeleteAccountForm("1") }) {
                Text("退会① 退会フォーム画面に遷移します")
            }
            Button(onClick = { viewModel.onClickDeleteConfirmForm("1") }) {
                Text("退会② 退会確認画面に遷移します")
            }
            Button(onClick = { viewModel.onClickDeleteComplete() }) {
                Text("退会③ 退会完了画面に遷移します")
            }
            Button(onClick = { viewModel.onClickNearbyStore() }) {
                Text("近くの使えるお店画面に遷移します")
            }
            Button(onClick = { viewModel.onClickSecurity() }) {
                Text("セキュリティ画面に遷移します")
            }
        }
    }
}
