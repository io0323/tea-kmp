package com.io.tea.android.ui.charge.confirm

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class ChargeCodeConfirmDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "charge/confirm"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                ChargeCodeConfirmPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
