package com.io.tea.android.nav.graph.builder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.io.tea.android.ui.unstart.UnstartDestination

class UnstartNavGraphBuilder(val builder: NavGraphBuilder) {
    fun build() {
        with(builder) {
            navigation(
                startDestination = UnstartDestination.value,
                route = UnstartDestination.graphRoute,
            ) {
                UnstartDestination.createNode(this)
            }
        }
    }
}