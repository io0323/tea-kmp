package com.io.tea.android.nav

import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

abstract class Destination(
    val value: String,
) {
    companion object {
        private const val DUMMY_URL = "dummy://destination/"

        // NOTE: Argを渡したい時に使用します
        inline fun <reified T> createNodeFromArguments(
            baseRoute: BaseRoute,
            navGraphBuilder: NavGraphBuilder,
            crossinline content: @Composable (T) -> Unit,
        ) {
            navGraphBuilder.composable(
                route = baseRoute.route,
                arguments = listOf(navArgument("json") { this.type = NavType.StringType }),
            ) { backStackEntry ->
                val json = requireNotNull(backStackEntry.arguments?.getString("json"))
                val route = Json.decodeFromString<T>(json)
                content(route)
            }
        }
    }

    open fun buildRoute(): String = value

    open fun navigate(navController: NavController) = navController.navigate(buildRoute())

    override fun toString(): String {
        return buildRoute()
    }

    fun buildRouteWithArgument(vararg arguments: Pair<String, String?>): String {
        val uriBuilder = "$DUMMY_URL$value".toUri()
            .buildUpon()
            .clearQuery()

        arguments.filter { it.second != null }.forEach {
            uriBuilder.appendQueryParameter(it.first, it.second)
        }

        return uriBuilder
            .build()
            .toString()
            .replaceFirst(DUMMY_URL, "")
    }

    inline fun <reified T> buildRouteWithArgument(args: T) =
        buildRouteWithArgument("json" to Json.encodeToString(args))
}

class BaseRoute(base: String) {
    val route = "$base?json={json}"
}