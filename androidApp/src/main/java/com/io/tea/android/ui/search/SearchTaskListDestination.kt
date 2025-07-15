package com.io.tea.android.ui.search

import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.io.tea.android.nav.Destination

internal data class SearchTeaListDestination(
    private val id: String,
) : Destination(route) {

    companion object {
        const val route = "my/regionList?id={id}"

        private fun NavArgumentBuilder.stringNullable() {
            type = NavType.StringType
            nullable = true
        }

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = listOf(
                    navArgument("id") { this.type = NavType.StringType },
                ),
            ) { backStackEntry ->
                val argsId = requireNotNull(backStackEntry.arguments?.getString("id"))
                MyRegionListPage(regionId = argsId)
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument(
        "id" to id,
    )
}