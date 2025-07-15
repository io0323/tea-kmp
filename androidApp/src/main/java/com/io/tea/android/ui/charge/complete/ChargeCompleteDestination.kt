package com.io.tea.android.ui.charge.complete

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class ChargeCompleteDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "charge/complete"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                ChargeCompletePage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
