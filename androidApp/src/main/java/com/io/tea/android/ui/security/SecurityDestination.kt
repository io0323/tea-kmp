package com.io.tea.android.ui.security

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class SecurityDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "security"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                SecurityPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}