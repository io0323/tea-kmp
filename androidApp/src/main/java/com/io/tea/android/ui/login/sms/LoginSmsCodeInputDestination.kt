package com.io.tea.android.ui.login.sms

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.io.tea.android.nav.Destination
import com.io.tea.android.ui.common.state.SmsSource

internal data class LoginSmsCodeInputDestination(
    private val source: SmsSource = SmsSource.PHONE,
) : Destination(route) {

    companion object {
        private const val PARAM_KEY = "source"
        const val route = "login/login?$PARAM_KEY={$PARAM_KEY}"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = route,
                arguments = listOf(
                    navArgument(PARAM_KEY) { type = NavType.StringType}
                ),
            ) { backStackEntry ->
                val argument = backStackEntry.arguments?.getString(PARAM_KEY)
                val source = argument?.let {
                    SmsSource.fromString(it)
                } ?: SmsSource.PHONE

                LogInSmsCodeInputPage(source = source)
            }
        }
    }

    override fun buildRoute() = buildRouteWithArgument(
        PARAM_KEY to source.value
    )
}
