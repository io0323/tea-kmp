package com.io.tea.android.nav.bottom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.io.tea.android.nav.graph.GraphDestination

abstract class BottomNavDestination(route: String) : GraphDestination(route) {

    @get:DrawableRes
    abstract val bottomNavIconRes: Int

    @get:StringRes
    abstract val bottomNavIcondescription: Int

    @get:StringRes
    abstract val bottomNavTitle: Int

    override fun navigate(navController: NavController) {
        val navBackStackEntry = navController.currentBackStackEntry
        val currentDestination = navBackStackEntry?.destination
        if (currentDestination?.route == this.value) {
            return
        }
        val selectBottomNavRoute = BottomNavType.selectBottomNav(navController)
        when (selectBottomNavRoute) {
            this.value -> {
                navController.popBackStack(this.buildRoute(), inclusive = false)
            }

            null -> {
                navController.navigate(this.graphRoute) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
                moveToDestinationGraph(
                    navController = navController,
                    graphRoute = this.graphRoute,
                )
            }

            else -> {
                moveToDestinationGraph(
                    navController = navController,
                    graphRoute = this.graphRoute,
                )
            }
        }
    }

    private fun moveToDestinationGraph(
        navController: NavController,
        graphRoute: String,
    ) {
        navController.navigate(graphRoute) {
            launchSingleTop = true
            restoreState = true
        }
    }
}
