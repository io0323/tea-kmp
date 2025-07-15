package com.io.tea.android.ui.user

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class UserInformationDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "user/information"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                UserInformationPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
