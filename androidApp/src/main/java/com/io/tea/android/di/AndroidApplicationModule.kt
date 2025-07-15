package com.io.tea.android.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.io.tea.android.nav.bottom.MainBottomNavViewModel
import com.io.tea.android.ui.MainViewModel
import com.io.tea.android.ui.account.auth.sms.AuthSmsViewModel
import com.io.tea.android.ui.account.create.AccountCreateViewModel
import com.io.tea.android.ui.charge.code.ChargeCodeInputViewModel
import com.io.tea.android.ui.charge.complete.ChargeCompleteViewModel
import com.io.tea.android.ui.charge.confirm.ChargeCodeConfirmViewModel
import com.io.tea.android.ui.charge.qr.ChargeQrScanViewModel
import com.io.tea.android.ui.codeinput.CodeInputViewModel
import com.io.tea.android.ui.delete.account.auth.DeleteAccountAuthViewModel
import com.io.tea.android.ui.delete.account.complete.DeleteAccountCompleteViewModel
import com.io.tea.android.ui.delete.account.confirm.DeleteAccountConfirmViewModel
import com.io.tea.android.ui.delete.account.form.DeleteAccountFormViewModel
import com.io.tea.android.ui.delete.region.confirm.DeleteRegionConfirmViewModel
import com.io.tea.android.ui.delete.region.form.DeleteRegionFormViewModel
import com.io.tea.android.ui.home.HomeViewModel
import com.io.tea.android.ui.login.LogInViewModel
import com.io.tea.android.ui.login.sms.LogInSmsCodeInputViewModel
import com.io.tea.android.ui.menu.DrawerMenuViewModel
import com.io.tea.android.ui.mock.MockDummyViewModel
import com.io.tea.android.ui.unstart.UnstartDestination
import com.io.tea.android.ui.unstart.UnstartViewModel
import com.io.tea.android.ui.notice.detail.NoticeDetailViewModel
import com.io.tea.android.ui.notice.list.NoticeListViewModel
import com.io.tea.android.ui.onboarding.personalized.PersonalizedViewModel
import com.io.tea.android.ui.onboarding.walkthrough.WalkThroughViewModel
import com.io.tea.android.ui.passcode.PasscodeSettingViewModel
import com.io.tea.android.ui.payment.PaymentQrScanViewModel
import com.io.tea.android.ui.payment.complete.PaymentCompleteViewModel
import com.io.tea.android.ui.payment.confirm.PaymentPointConfirmViewModel
import com.io.tea.android.ui.payment.point.PaymentPointInputViewModel
import com.io.tea.android.ui.permission.NotificationPermissionViewModel
import com.io.tea.android.ui.region.detail.RegionDetailViewModel
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.ui.region.home.MyTeaHomeViewModel
import com.io.tea.android.ui.region.list.RegionListViewModel
import com.io.tea.android.ui.search.SearchTeaListViewModel
import com.io.tea.android.ui.security.SecurityViewModel
import com.io.tea.android.ui.history.HistoryDestination
import com.io.tea.android.ui.history.HistoryViewModel
import com.io.tea.android.ui.user.UserInformationViewModel
import com.io.tea.android.ui.user.edit.UserInformationEditViewModel
import com.io.tea.android.ui.user.input.UserInformationInputViewModel
import com.io.tea.android.ui.webview.WebViewViewModel
import com.io.tea.di.apiModule
import com.io.tea.di.repositoryModule
import com.io.tea.di.useCaseModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val androidAppModule = module {
    viewModel {
        MainViewModel()
    }
    viewModel {
        HomeViewModel(get(), get(), get())
    }
    viewModel {
        LogInViewModel()
    }
    viewModel {
        LogInSmsCodeInputViewModel(get())
    }
    viewModel {
        AuthSmsViewModel()
    }
    viewModel {
        WalkThroughViewModel()
    }
    viewModel {
        PersonalizedViewModel()
    }
    viewModel {
        PersonalizedViewModel()
    }
    viewModel {
        NotificationPermissionViewModel()
    }
    viewModel {
        UserInformationViewModel()
    }
    viewModel {
        UserInformationInputViewModel()
    }
    viewModel {
        UserInformationEditViewModel()
    }
    viewModel {
        AccountCreateViewModel(get())
    }
    viewModel {
        MyTeaHomeViewModel()
    }
    viewModel {
        RegionListViewModel(get(), get(), get())
    }
    viewModel { (regionId: String) ->
        RegionDetailViewModel(
            regionId,
            get()
        )
    }
    viewModel {
        PaymentQrScanViewModel()
    }
    viewModel {
        ChargeQrScanViewModel()
    }
    viewModel {
        ChargeCodeConfirmViewModel()
    }
    viewModel {
        ChargeCompleteViewModel()
    }
    viewModel {
        PaymentPointInputViewModel()
    }
    viewModel {
        PaymentPointConfirmViewModel()
    }
    viewModel {
        PaymentCompleteViewModel()
    }
    viewModel {
        UnstartViewModel()
    }
    viewModel {
        HistoryViewModel(get())
    }
    viewModel {
        CodeInputViewModel()
    }
    viewModel {
        NoticeListViewModel(get(), get())
    }
    viewModel {
        NoticeDetailViewModel()
    }
    viewModel {
        WebViewViewModel(get(), get())
    }
    viewModel {
        DrawerMenuViewModel()
    }
    viewModel {
        DeleteRegionFormViewModel()
    }
    viewModel {
        DeleteRegionConfirmViewModel()
    }
    viewModel {
        DeleteAccountAuthViewModel(get())
    }
    viewModel {
        DeleteAccountFormViewModel()
    }
    viewModel {
        DeleteAccountConfirmViewModel()
    }
    viewModel {
        DeleteAccountCompleteViewModel()
    }
    viewModel {
        SearchTeaListViewModel(get())
    }
    viewModel {
        SecurityViewModel()
    }
    viewModel {
        PasscodeSettingViewModel()
    }
    viewModel {
        ChargeCodeInputViewModel()
    }

    // TODO : MockDummy
    viewModel {
        MockDummyViewModel()
    }
    viewModel {
        MainBottomNavViewModel(
            listOf(
                MyTeaHomeDestination,
                UnstartDestination,
                HistoryDestination,
            ),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
val KoinModules: List<Module> = listOf(
    androidAppModule,
    apiModule,
    repositoryModule,
    useCaseModule,
)