package com.io.tea.android.nav.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.io.tea.android.nav.graph.builder.HistoryNavGraphBuilder
import com.io.tea.android.nav.graph.builder.MainNavGraphBuilder
import com.io.tea.android.nav.graph.builder.MyTeaNavGraphBuilder
import com.io.tea.android.nav.graph.builder.UnstartNavGraphBuilder
import com.io.tea.android.ui.login.LoginDestination

@Composable
internal fun MainNavGraph(
    navController: NavHostController,
    startDestination: String = LoginDestination.graphRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        MainNavGraphBuilder(this).build()
        MyTeaNavGraphBuilder(this).build()
        UnstartNavGraphBuilder(this).build()
        HistoryNavGraphBuilder(this).build()
    }
}
