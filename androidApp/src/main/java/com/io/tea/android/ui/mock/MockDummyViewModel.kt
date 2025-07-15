package com.io.tea.android.ui.mock

import androidx.lifecycle.ViewModel
import com.io.tea.android.ui.delete.region.form.DeleteRegionFormDestination
import com.io.tea.android.ui.permission.NotificationPermissionDestination
import com.io.tea.android.ui.user.input.UserInformationInputDestination
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.charge.code.ChargeCodeInputDestination
import com.io.tea.android.ui.charge.complete.ChargeCompleteDestination
import com.io.tea.android.ui.charge.confirm.ChargeCodeConfirmDestination
import com.io.tea.android.ui.charge.qr.ChargeQrScanDestination
import com.io.tea.android.ui.codeinput.CodeInputDestination
import com.io.tea.android.ui.common.state.SmsSource
import com.io.tea.android.ui.delete.account.auth.DeleteAccountAuthDestination
import com.io.tea.android.ui.delete.account.complete.DeleteAccountCompleteDestination
import com.io.tea.android.ui.delete.account.confirm.DeleteAccountConfirmDestination
import com.io.tea.android.ui.delete.account.form.DeleteAccountFormDestination
import com.io.tea.android.ui.delete.region.confirm.DeleteRegionConfirmDestination
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.ui.login.LoginDestination
import com.io.tea.android.ui.login.sms.LoginSmsCodeInputDestination
import com.io.tea.android.ui.menu.DrawerMenuDestination
import com.io.tea.android.ui.search.SearchTeaListDestination
import com.io.tea.android.ui.unstart.UnstartDestination
import com.io.tea.android.ui.notice.list.NoticeListDestination
import com.io.tea.android.ui.onboarding.personalized.PersonalizedDestination
import com.io.tea.android.ui.onboarding.walkthrough.WalkThroughDestination
import com.io.tea.android.ui.passcode.PasscodeSettingDestination
import com.io.tea.android.ui.payment.PaymentQrScanDestination
import com.io.tea.android.ui.payment.complete.PaymentCompleteDestination
import com.io.tea.android.ui.payment.confirm.PaymentPointConfirmDestination
import com.io.tea.android.ui.payment.point.PaymentPointInputDestination
import com.io.tea.android.ui.account.create.AccountCreateDestination
import com.io.tea.android.ui.region.detail.MyTeaDetailDestination
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.ui.region.list.MyTeaListDestination
import com.io.tea.android.ui.security.SecurityDestination
import com.io.tea.android.ui.account.auth.sms.AuthSmsDestination
import com.io.tea.android.ui.history.HistoryDestination
import com.io.tea.android.ui.user.edit.UserInformationEditDestination
import com.io.tea.android.ui.user.UserInformationDestination

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MockDummyViewModel : ViewModel() {

    private val _navigationStateFlow = MutableStateFlow<Destination?>(null)
    val navigationStateFlow: StateFlow<Destination?> = _navigationStateFlow

    // ウォークスルー画面に遷移します
    fun onClickWalkThroughUsage() {
        _navigationStateFlow.value = WalkThroughDestination
    }

    // パーソナライズ画面に遷移します
    fun onClickPersonalized() {
        _navigationStateFlow.value = PersonalizedDestination("")
    }

    // プッシュ通知要求画面に遷移します
    fun onClickNotificationPermission() {
        _navigationStateFlow.value = NotificationPermissionDestination("")
    }

    // アカウント作成画面に遷移します
    fun onClickPhoneNumberInput() {
        _navigationStateFlow.value = AccountCreateDestination("")
    }

    // アカウント情報画面に遷移します
    fun onClickUserInformation() {
        _navigationStateFlow.value = UserInformationDestination("")
    }

    // アカウント情報画面に遷移します
    fun onClickUserInformationEdit() {
        _navigationStateFlow.value = UserInformationEditDestination("")
    }

    // SMS認証(新規登録)画面に遷移します
    fun onClickSmsCodeInput() {
        _navigationStateFlow.value = AuthSmsDestination("")
    }

    // ホーム画面に遷移します
    fun onClickHome(id: String) {
        _navigationStateFlow.value = HomeDestination(id)
    }

    // 新規登録画面に遷移します
    fun onClickUserInformationInput() {
        _navigationStateFlow.value = UserInformationInputDestination
    }

    // ログイン画面に遷移します
    fun onClickLOptIn() {
        _navigationStateFlow.value = LoginDestination
    }

    // SMS認証(ログイン)画面に遷移します
    fun onClickLogInSms(source: SmsSource) {
        _navigationStateFlow.value = LoginSmsCodeInputDestination(source)
    }

    // パスコード設定画面に遷移します
    fun onClickPasscodeSetting(id: String) {
        _navigationStateFlow.value = PasscodeSettingDestination(id)
    }

    // MyTeaTop画面に遷移します
    fun onClickMyTeaTop(id: String) {
        _navigationStateFlow.value = MyTeaHomeDestination
    }

    // Lotリスト画面に遷移します
    fun onClickRegionList(id: String) {
        _navigationStateFlow.value = MyTeaListDestination(id)
    }

    // Lot詳細画面に遷移します
    fun onClickRegionDetail(id: String) {
        _navigationStateFlow.value = MyTeaDetailDestination(id)
    }

    // MYLot一覧画面に遷移します
    fun onClickMyRegionList(id: String) {
        _navigationStateFlow.value = SearchTeaListDestination(id)
    }

    // 取引履歴＆Lot有効期限 画面に遷移します
    fun onClickTransactionHistory() {
        _navigationStateFlow.value = HistoryDestination
    }

    // 決済QRカメラ画面に遷移します
    fun onClickPaymentQrScan() {
        _navigationStateFlow.value = PaymentQrScanDestination()
    }

    // 指定Lot入力画面に遷移します
    fun onClickCodeInput() {
        _navigationStateFlow.value = CodeInputDestination()
    }

    // LotQRカメラ画面に遷移します
    fun onClickChargeQrScan() {
        _navigationStateFlow.value = ChargeQrScanDestination()
    }

    // Lotコード入力画面に遷移します
    fun onClickChargeCodeInput() {
        _navigationStateFlow.value = ChargeCodeInputDestination()
    }

    // Lot確認画面に遷移します
    fun onClickChargeCodeConfirm() {
        _navigationStateFlow.value = ChargeCodeConfirmDestination()
    }

    // Lot完了画面に遷移します
    fun onClickChargeComplete() {
        _navigationStateFlow.value = ChargeCompleteDestination()
    }

    // 決済Lot入力画面に遷移します
    fun onClickPaymentPointInput() {
        _navigationStateFlow.value = PaymentPointInputDestination()
    }

    // 決済Lot確認画面に遷移します
    fun onClickPaymentPointConfirm() {
        _navigationStateFlow.value = PaymentPointConfirmDestination()
    }

    // 決済完了画面に遷移します
    fun onClickPaymentComplete() {
        _navigationStateFlow.value = PaymentCompleteDestination()
    }

    // お知らせ一覧画面に遷移します
    fun onClickNoticeList(id: String) {
        _navigationStateFlow.value = NoticeListDestination(id)
    }

    // グローバルメニュー画面に遷移します
    fun onClickDrawerMenu(id: String) {
        _navigationStateFlow.value = DrawerMenuDestination(id)
    }

    // Lot削除① 削除フォーム画面に遷移します
    fun onClickDeleteRegionForm(id: String) {
        _navigationStateFlow.value = DeleteRegionFormDestination(id)
    }

    // Lot削除② 削除確認画面に遷移します
    fun onClickDeleteRegionConfirm(id: String) {
        _navigationStateFlow.value = DeleteRegionConfirmDestination(id)
    }

    // 退会 アカウント認証画面に遷移します
    fun onClickDeleteAccountAuthForm(source: SmsSource) {
        _navigationStateFlow.value = DeleteAccountAuthDestination(source)
    }

    // 退会① 退会フォーム画面に遷移します
    fun onClickDeleteAccountForm(id: String) {
        _navigationStateFlow.value = DeleteAccountFormDestination(id)
    }

    // 退会② 退会確認画面に遷移します
    fun onClickDeleteConfirmForm(id: String) {
        _navigationStateFlow.value = DeleteAccountConfirmDestination(id)
    }

    // 退会③ 退会完了画面に遷移します
    fun onClickDeleteComplete() {
        _navigationStateFlow.value = DeleteAccountCompleteDestination()
    }

    // 近くの使えるお店画面に遷移します
    fun onClickNearbyStore() {
        _navigationStateFlow.value = UnstartDestination
    }

    // セキュリティー画面に遷移します
    fun onClickSecurity() {
        _navigationStateFlow.value = SecurityDestination()
    }

    fun completeNavigation() {
        _navigationStateFlow.value = null
    }
}