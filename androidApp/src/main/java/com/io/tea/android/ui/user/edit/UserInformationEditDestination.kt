package com.io.tea.android.ui.user.edit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class UserInformationEditDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "user/edit"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                UserInformationEditPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
