package com.io.tea.android.nav.navigator

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.io.tea.android.nav.Destination

interface Navigator {
    fun navigateTo(destination: Destination)

    fun popBack()

    fun getCurrentNavBackStack(): NavBackStackEntry?

    fun getPreviousNavBackStack(): NavBackStackEntry?

    val navController: NavController
}
