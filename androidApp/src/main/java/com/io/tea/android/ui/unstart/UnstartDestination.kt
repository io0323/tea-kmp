package com.io.tea.android.ui.unstart

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.ui.region.list.MyTeaListDestination
import com.io.tea.android.nav.bottom.BottomNavDestination
import com.io.tea.android.nav.bottom.BottomNavType

object UnstartDestination : BottomNavDestination(BottomNavType.BOTTOM_NAV_UNSTART.route) {
    fun createNode(builder: NavGraphBuilder) {
        builder.composable(value) {
            NearbyTeaPage()
        }
    }

    @SuppressLint("RestrictedApi")
    override fun navigate(navController: NavController) {
        val currentBackStackEntry = navController.currentBackStackEntry
        val currentDestination = currentBackStackEntry?.destination
        val selectBottomNavRoute = BottomNavType.selectBottomNav(navController)
        if (selectBottomNavRoute == this.value) {
            navController.currentBackStack.value.findLast { entry ->
                entry.destination.route?.startsWith(MyTeaListDestination.route) ?: false
            }?.let { backStack ->
                if (currentDestination != backStack.destination) {
                    navController.popBackStack(
                        destinationId = backStack.destination.id,
                        inclusive = false,
                    )
                    return
                }
            }
        }
        super.navigate(navController)
    }

    override val bottomNavIconRes: Int
        get() = BottomNavType.BOTTOM_NAV_UNSTART.iconRes
    override val bottomNavIcondescription: Int
        get() = BottomNavType.BOTTOM_NAV_UNSTART.icondescriptionRes
    override val bottomNavTitle: Int
        get() = BottomNavType.BOTTOM_NAV_UNSTART.titleRes
}
