package com.io.tea.android.ui.account.auth.sms

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

const val route = "auth/sms"

internal data class AuthSmsDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                AuthSmsPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
