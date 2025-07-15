package com.io.tea.android.nav.graph.builder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.io.tea.android.nav.graph.createTransition
import com.io.tea.android.ui.delete.region.form.DeleteRegionFormDestination
import com.io.tea.android.ui.charge.code.ChargeCodeInputDestination
import com.io.tea.android.ui.charge.complete.ChargeCompleteDestination
import com.io.tea.android.ui.charge.confirm.ChargeCodeConfirmDestination
import com.io.tea.android.ui.charge.qr.ChargeQrScanDestination
import com.io.tea.android.ui.codeinput.CodeInputDestination
import com.io.tea.android.ui.delete.account.auth.DeleteAccountAuthDestination
import com.io.tea.android.ui.delete.account.complete.DeleteAccountCompleteDestination
import com.io.tea.android.ui.delete.account.confirm.DeleteAccountConfirmDestination
import com.io.tea.android.ui.delete.account.form.DeleteAccountFormDestination
import com.io.tea.android.ui.delete.region.confirm.DeleteRegionConfirmDestination
import com.io.tea.android.ui.home.HomeDestination
import com.io.tea.android.ui.login.LoginDestination
import com.io.tea.android.ui.login.sms.LoginSmsCodeInputDestination
import com.io.tea.android.ui.menu.DrawerMenuDestination
import com.io.tea.android.ui.mock.MockDummyDestination
import com.io.tea.android.ui.search.SearchTeaListDestination
import com.io.tea.android.ui.notice.detail.NoticeDetailDestination
import com.io.tea.android.ui.notice.list.NoticeListDestination
import com.io.tea.android.ui.onboarding.personalized.PersonalizedDestination
import com.io.tea.android.ui.onboarding.walkthrough.WalkThroughDestination
import com.io.tea.android.ui.passcode.PasscodeSettingDestination
import com.io.tea.android.ui.payment.PaymentQrScanDestination
import com.io.tea.android.ui.payment.complete.PaymentCompleteDestination
import com.io.tea.android.ui.payment.confirm.PaymentPointConfirmDestination
import com.io.tea.android.ui.payment.point.PaymentPointInputDestination
import com.io.tea.android.ui.permission.NotificationPermissionDestination
import com.io.tea.android.ui.account.create.AccountCreateDestination
import com.io.tea.android.ui.security.SecurityDestination
import com.io.tea.android.ui.account.auth.sms.AuthSmsDestination
import com.io.tea.android.ui.user.UserInformationDestination
import com.io.tea.android.ui.user.edit.UserInformationEditDestination
import com.io.tea.android.ui.user.input.UserInformationInputDestination
import com.io.tea.android.ui.webview.WebViewDestination

class MainNavGraphBuilder(val builder: NavGraphBuilder) {
    fun build() {
        with(builder) {
            navigation(
                startDestination = LoginDestination.value,
                route = LoginDestination.graphRoute,
            ) {
                MockDummyDestination.createNode(this, createTransition(MockDummyDestination.value))
                WalkThroughDestination.createNode(
                    this,
                    createTransition(WalkThroughDestination.value)
                )
                LoginDestination.createNode(this, createTransition(LoginDestination.value))
                UserInformationInputDestination.createNode(
                    this,
                    createTransition(UserInformationInputDestination.value)
                )
                PersonalizedDestination.createNode(this)
                UserInformationDestination.createNode(this)
                CodeInputDestination.createNode(this)
                LoginSmsCodeInputDestination.createNode(this)
                UserInformationEditDestination.createNode(this)
                AccountCreateDestination.createNode(this)
                PaymentQrScanDestination.createNode(this)
                ChargeQrScanDestination.createNode(this)
                ChargeCodeConfirmDestination.createNode(this)
                ChargeCompleteDestination.createNode(this)
                PaymentPointInputDestination.createNode(this)
                PaymentPointConfirmDestination.createNode(this)
                PaymentCompleteDestination.createNode(this)
                AuthSmsDestination.createNode(this)
                PersonalizedDestination.createNode(this)
                NotificationPermissionDestination.createNode(this)
                NoticeListDestination.createNode(this)
                NoticeDetailDestination.createNode(this)
                WebViewDestination.createNode(this)
                DrawerMenuDestination.createNode(this)
                DeleteRegionFormDestination.createNode(this)
                DeleteRegionConfirmDestination.createNode(this)
                DeleteAccountAuthDestination.createNode(this)
                DeleteAccountFormDestination.createNode(this)
                DeleteAccountConfirmDestination.createNode(this)
                SearchTeaListDestination.createNode(this)
                SecurityDestination.createNode(this)
                HomeDestination.createNode(this)
                DeleteAccountCompleteDestination.createNode(this)
                PasscodeSettingDestination.createNode(this)
                ChargeCodeInputDestination.createNode(this)
            }
        }
    }
}