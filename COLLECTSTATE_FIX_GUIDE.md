# collectAsStateWithLifecycle API Compatibility Fix

## Problem
`collectAsStateWithLifecycle` requires API level 26+ (Android O), but the app's minSdkVersion is 24. This causes lint errors for API levels 24 and 25.

## Solution
Use conditional version checking to call `collectAsStateWithLifecycle` for API 26+ and `collectAsState` for API 24/25.

## Pattern Applied

### 1. Add Required Imports
```kotlin
import android.os.Build
import androidx.compose.runtime.collectAsState
// Also import any state classes needed for initial values
```

### 2. Replace collectAsStateWithLifecycle Calls
**Before:**
```kotlin
val destination by viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
```

**After:**
```kotlin
val destination by if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    viewModel.navigationStateFlow.collectAsStateWithLifecycle(lifecycleOwner)
} else {
    viewModel.navigationStateFlow.collectAsState(initial = null)
}
```

## Common Initial Values Reference

| StateFlow Type | Initial Value |
|----------------|---------------|
| `StateFlow<Destination?>` | `null` |
| `StateFlow<String?>` | `null` |
| `StateFlow<String>` | `""` |
| `StateFlow<Boolean>` | `false` |
| `StateFlow<List<T>>` | `emptyList()` |
| `StateFlow<TransactionHistoryUseCaseState>` | `TransactionHistoryUseCaseState.Initial` |
| `StateFlow<TransactionHistoryUiState>` | `TransactionHistoryUiState(isLoad = false)` |
| `StateFlow<HomeUseCaseState>` | `HomeUseCaseState.Initial` |
| `StateFlow<HomeUiState>` | `HomeUiState()` |
| `StateFlow<HomeDisplayState>` | `HomeDisplayState()` |
| `StateFlow<MyRegionListUseCaseState>` | `MyRegionListUseCaseState.Initial` |
| `StateFlow<SearchTeaListViewModelUiState>` | `SearchTeaListViewModelUiState(myRegionList = listOf(), isLoad = true, isRefresh = false, isError = false)` |
| `StateFlow<LogInModel>` | `LogInModel.default` |

## Files Fixed (6/42)

✅ **Completed:**
1. `androidApp/src/main/java/com/io/tea/android/ui/history/HistoryPage.kt`
2. `androidApp/src/main/java/com/io/tea/android/ui/home/HomePage.kt`
3. `androidApp/src/main/java/com/io/tea/android/ui/search/SearchTaskListPage.kt`
4. `androidApp/src/main/java/com/io/tea/android/nav/AppCompositionLocal.kt`
5. `androidApp/src/main/java/com/io/tea/android/nav/bottom/BottomNavigation.kt`
6. `androidApp/src/main/java/com/io/tea/android/ui/login/LoginPage.kt`

## Remaining Files to Fix (36/42)

📋 **High Priority:**
- `androidApp/src/main/java/com/io/tea/android/ui/notice/list/NoticeListPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/notice/detail/NoticeDetailPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/security/SecurityPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/user/UserInformationPage.kt`

📋 **Remaining Files:**
- `androidApp/src/main/java/com/io/tea/android/ui/unstart/UnstartPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/region/home/MyTaskHomePage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/region/detail/RegionDetailPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/region/list/RegionListPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/delete/region/confirm/DeleteRegionConfirmPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/delete/region/form/DeleteRegionFormPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/delete/account/auth/DeleteAccountAuthPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/delete/account/confirm/DeleteAccountConfirmPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/delete/account/complete/DeleteAccountCompletePage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/delete/account/form/DeleteAccountFormPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/onboarding/personalized/PersonalizedPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/onboarding/coachmark/WalkThroughCoachMarkPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/onboarding/walkthrough/WalkThroughPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/menu/DrawerMenuPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/user/input/UserInformationInputPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/user/edit/UserInformationEditPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/passcode/PasscodeSettingPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/permission/NotificationPermissionPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/codeinput/CodeInputPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/charge/confirm/ChargeCodeConfirmPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/charge/complete/ChargeCompletePage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/charge/code/ChargeCodeInputPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/charge/qr/ChargeQrScanPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/mock/MockDummyHomePage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/payment/point/PaymentPointInputPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/payment/confirm/PaymentPointConfirmPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/payment/complete/PaymentCompletePage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/payment/PaymentQrScanPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/webview/WebViewPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/account/auth/sms/AuthSmsPage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/account/create/AccountCreatePage.kt`
- `androidApp/src/main/java/com/io/tea/android/ui/login/sms/LoginSmsCodeInputPage.kt`

## Steps to Apply Fix to Remaining Files

1. **Open the file** to be fixed
2. **Add imports** for `Build` and `collectAsState`
3. **Identify StateFlow types** by checking the ViewModel
4. **Choose appropriate initial values** from the reference table above or by examining the ViewModel's initial state
5. **Replace each collectAsStateWithLifecycle call** with the conditional pattern
6. **Test the changes** to ensure they compile and work correctly

## Testing

Once all files are fixed, the app should:
- Compile without lint errors for minSdkVersion 24
- Work correctly on API levels 24-25 using `collectAsState`
- Work correctly on API levels 26+ using `collectAsStateWithLifecycle`
- Maintain all existing functionality without breaking changes

## Notes

- This fix maintains full backward compatibility
- No functional changes to the app behavior
- Type-safe initial values prevent runtime crashes
- The pattern is consistent across all files for maintainability