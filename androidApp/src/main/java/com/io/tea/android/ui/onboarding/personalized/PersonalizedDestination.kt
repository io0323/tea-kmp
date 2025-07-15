package com.io.tea.android.ui.onboarding.personalized

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class PersonalizedDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "onBoarding/Personalized"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                PersonalizedPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
