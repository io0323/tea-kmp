package com.io.tea.android.ui.mock

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.bottom.BottomNavDestination
import com.io.tea.android.nav.bottom.BottomNavType
import com.io.tea.android.nav.graph.Transition
import com.io.tea.android.ui.region.list.MyTeaListDestination

object MockDummyDestination : BottomNavDestination(BottomNavType.BOTTOM_NAV_MOCK_DUMMY.route) {
    fun createNode(
        builder: NavGraphBuilder,
        transition: Transition
    ) {
        builder.composable(
            route = transition.route,
            enterTransition = { transition.enterTransition },
            exitTransition = { transition.exitTransition },
            popEnterTransition = { transition.popEnterTransition },
            popExitTransition = { transition.popExitTransition }
        ) {
            MockDummyPage()
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
        get() = BottomNavType.BOTTOM_NAV_MOCK_DUMMY.iconRes
    override val bottomNavIcondescription: Int
        get() = BottomNavType.BOTTOM_NAV_MOCK_DUMMY.icondescriptionRes
    override val bottomNavTitle: Int
        get() = BottomNavType.BOTTOM_NAV_MOCK_DUMMY.titleRes
}
