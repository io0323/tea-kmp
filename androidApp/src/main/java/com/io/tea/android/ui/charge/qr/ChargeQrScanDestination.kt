package com.io.tea.android.ui.charge.qr

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.io.tea.android.nav.Destination

internal data class ChargeQrScanDestination(
    private val id: String = "dummy",
) : Destination(route) {

    companion object {
        const val route = "charge/qrScan"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = emptyList(),
            ) { backStackEntry ->
                ChargeQrScanPage()
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument()
}
