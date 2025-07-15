package com.io.tea.android.ui.codeinput

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class CodeInputDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "code/input"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                CodeInputPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
