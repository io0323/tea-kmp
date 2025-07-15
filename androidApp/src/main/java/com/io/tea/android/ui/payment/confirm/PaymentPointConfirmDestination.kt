package com.io.tea.android.ui.payment.confirm

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

data class PaymentPointConfirmDestination(
    private val id: String = "dummy",
) : Destination(route) {
    companion object {
        const val route = "payment/confirm"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                PaymentPointConfirmPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
