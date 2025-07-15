package com.io.tea.android.ui.payment.point

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class PaymentPointInputDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "payment/point/input"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                PaymentPointInputPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
