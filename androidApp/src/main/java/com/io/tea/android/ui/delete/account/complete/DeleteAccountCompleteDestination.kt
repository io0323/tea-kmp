package com.io.tea.android.ui.delete.account.complete

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class DeleteAccountCompleteDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "delete/accountComplete"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                DeleteAccountCompletePage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}