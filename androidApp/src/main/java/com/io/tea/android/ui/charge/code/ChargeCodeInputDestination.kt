package com.io.tea.android.ui.charge.code

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class ChargeCodeInputDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "charge/code"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                ChargeCodeInputPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
