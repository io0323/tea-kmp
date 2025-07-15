package com.io.tea.android.nav.navigator

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.io.tea.android.nav.Destination

object NavigatorFactory {

    fun create(navController: NavController): Navigator {
        return NavigatorImpl(navController)
    }
}

private class NavigatorImpl(
    val controller: NavController,
) : Navigator {

    override fun navigateTo(destination: Destination) {
        destination.navigate(this.controller)
    }

    override fun popBack() {
        this.controller.popBackStack()
    }

    override fun getCurrentNavBackStack(): NavBackStackEntry? {
        return this.controller.currentBackStackEntry
    }

    override fun getPreviousNavBackStack(): NavBackStackEntry? {
        return this.controller.previousBackStackEntry
    }

    override val navController: NavController
        get() = this.controller
}
