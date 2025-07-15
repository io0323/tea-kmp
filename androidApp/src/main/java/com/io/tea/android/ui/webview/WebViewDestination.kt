package com.io.tea.android.ui.webview

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.io.tea.android.nav.Destination
import java.net.URLDecoder
import java.nio.charset.Charset

internal data class WebViewDestination(
    val url: String,
    val title: String? = null,
) : Destination(route) {

    companion object {
        const val route = "openurl?url={url}&title={title}"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route,
                arguments = listOf(
                    navArgument("url") { type = NavType.StringType },
                    navArgument("title") {
                        type = NavType.StringType
                        nullable = true
                    },
                ),
            ) { backStackEntry ->
                val encoded = requireNotNull(backStackEntry.arguments?.getString("url"))
                val url = URLDecoder.decode(encoded, Charset.defaultCharset().name())
                val title = backStackEntry.arguments?.getString("title")
                WebViewPage(url, title)
            }
        }
    }

    override fun buildRoute(): String =
        buildRouteWithArgument(
            "url" to url,
            "title" to title,
        )
}

