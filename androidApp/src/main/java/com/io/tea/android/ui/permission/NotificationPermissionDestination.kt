package com.io.tea.android.ui.permission

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class NotificationPermissionDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "permission/notification"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                NotificationPermissionPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
