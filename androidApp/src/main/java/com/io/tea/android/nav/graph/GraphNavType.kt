package com.io.tea.android.nav.graph

import android.annotation.SuppressLint
import androidx.navigation.NavController

enum class GraphNavType(
    val route: String,
) {
    NAV_WALK_THROUGH(
        "onboarding/walkthrough",
    ),
    NAV_USER_INFO_INPUT(
        "user/input",
    ),
    NAV_LOGIN(
        "app/login",
    );

    companion object {
        @SuppressLint("RestrictedApi")
        fun selectNav(navController: NavController): String? {
            return navController.currentBackStack.value.lastOrNull { backStack ->
                entries.any {
                    backStack.destination.route == it.route
                }
            }?.destination?.route
        }

        fun selectNavType(navController: NavController): GraphNavType? {
            return convertFromRoute(selectNav(navController))
        }

        private fun convertFromRoute(route: String?): GraphNavType? {
            return when (route) {
                NAV_WALK_THROUGH.route -> NAV_WALK_THROUGH
                NAV_USER_INFO_INPUT.route -> NAV_USER_INFO_INPUT
                NAV_LOGIN.route -> NAV_LOGIN
                else -> null
            }
        }
    }
}
