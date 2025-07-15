package com.io.tea.android.ui.passcode

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class PasscodeSettingDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "passcode/setting"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                PasscodeSettingPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
