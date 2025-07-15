package com.io.tea.android.nav.bottom

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.io.tea.android.R

enum class BottomNavType(
    val route: String,
    @DrawableRes val iconRes: Int,
    @StringRes val icondescriptionRes: Int,
    @StringRes val titleRes: Int
) {
    BOTTOM_NAV_MY_Tea(
        "my/Tea",
        android.R.drawable.ic_menu_close_clear_cancel,
        R.string.bottom_nav__my_Tea,
        R.string.bottom_nav__my_Tea,
    ),
    BOTTOM_NAV_UNSTART(
        "unstart/Tea",
        android.R.drawable.ic_menu_close_clear_cancel,
        R.string.bottom_nav__unstart_Tea,
        R.string.bottom_nav__unstart_Tea
    ),
    BOTTOM_NAV_Tea_HISTORY(
        "Tea/history",
        android.R.drawable.ic_menu_close_clear_cancel,
        R.string.bottom_nav__history,
        R.string.bottom_nav__history
    ),
    BOTTOM_NAV_MOCK_DUMMY(
        "mock/dummy",
        android.R.drawable.ic_menu_close_clear_cancel,
        R.string.bottom_nav__mock_dummy,
        R.string.bottom_nav__mock_dummy,
    );

    companion object {
        @SuppressLint("RestrictedApi")
        fun selectBottomNav(navController: NavController): String? {
            return navController.currentBackStack.value.lastOrNull { backStack ->
                entries.any {
                    backStack.destination.route == it.route
                }
            }?.destination?.route
        }

        fun selectBottomNavType(navController: NavController): BottomNavType? {
            return convertFromRoute(selectBottomNav(navController))
        }

        private fun convertFromRoute(route: String?): BottomNavType? {
            return when (route) {
                BOTTOM_NAV_MY_Tea.route -> BOTTOM_NAV_MY_Tea
                BOTTOM_NAV_UNSTART.route -> BOTTOM_NAV_UNSTART
                BOTTOM_NAV_Tea_HISTORY.route -> BOTTOM_NAV_Tea_HISTORY
                BOTTOM_NAV_MOCK_DUMMY.route -> BOTTOM_NAV_MOCK_DUMMY
                else -> null
            }
        }
    }
}
