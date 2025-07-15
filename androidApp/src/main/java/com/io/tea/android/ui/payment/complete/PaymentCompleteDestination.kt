package com.io.tea.android.ui.payment.complete

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class PaymentCompleteDestination(
    private val id: String = "dummy",
) : Destination(route) {
    companion object {
        const val route = "payment/complete"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                PaymentCompletePage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
