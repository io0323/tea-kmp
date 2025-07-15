package com.io.tea.android.ui.delete.region.confirm

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.io.tea.android.nav.Destination

internal data class DeleteRegionConfirmDestination(
    private val id: String,
) : Destination(route) {

    companion object {
        const val route = "delete/regionConfirm?id={id}"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = listOf(
                    navArgument("id") { this.type = NavType.StringType },
                ),
            ) { backStackEntry ->
                val argsId = requireNotNull(backStackEntry.arguments?.getString("id"))
                DeleteRegionConfirmPage(
                    noticeId = argsId,
                )
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument(
        "id" to id,
    )
}
