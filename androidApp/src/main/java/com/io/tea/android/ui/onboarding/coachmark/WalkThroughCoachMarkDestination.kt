package com.io.tea.android.ui.onboarding.coachmark

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

class WalkThroughCoachMarkDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "walkthrough/coach_mark"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                WalkThroughCoachMarkPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
