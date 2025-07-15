package com.io.tea.android.ui.delete.account.form

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.io.tea.android.nav.Destination

internal data class DeleteAccountFormDestination(
    private val id: String,
) : Destination(route) {

    companion object {
        const val route = "delete/accountForm?id={id}"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = listOf(
                    navArgument("id") { this.type = NavType.StringType },
                ),
            ) { backStackEntry ->
                val argsId = requireNotNull(backStackEntry.arguments?.getString("id"))
                DeleteAccountFormPage(
                    noticeId = argsId,
                )
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument(
        "id" to id,
    )
}
