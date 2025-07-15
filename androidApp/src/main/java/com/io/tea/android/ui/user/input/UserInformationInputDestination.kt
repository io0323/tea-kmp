package com.io.tea.android.ui.user.input

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.graph.GraphDestination
import com.io.tea.android.nav.graph.GraphNavType
import com.io.tea.android.nav.graph.Transition
import com.io.tea.android.ui.region.list.MyTeaListDestination

object UserInformationInputDestination : GraphDestination(GraphNavType.NAV_USER_INFO_INPUT.route) {
    fun createNode(
        builder: NavGraphBuilder,
        transition: Transition,
    ) {
        builder.composable(
            route = transition.route,
            enterTransition = { transition.enterTransition },
            exitTransition = { transition.exitTransition },
            popEnterTransition = { transition.popEnterTransition },
            popExitTransition = { transition.popExitTransition }
        ) {
            UserInformationInputPage()
        }
    }

    @SuppressLint("RestrictedApi")
    override fun navigate(navController: NavController) {
        val currentBackStackEntry = navController.currentBackStackEntry
        val currentDestination = currentBackStackEntry?.destination
        val selectBottomNavRoute = GraphNavType.selectNav(navController)
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
}
