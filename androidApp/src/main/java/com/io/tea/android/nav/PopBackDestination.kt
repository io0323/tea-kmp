package com.io.tea.android.nav

import androidx.navigation.NavController
import com.io.tea.android.nav.Destination

object PopBackDestination : Destination("pop_back") {
    override fun navigate(navController: NavController) {
        navController.popBackStack()
    }
}
