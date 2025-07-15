package com.io.tea.android.nav.graph.builder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.io.tea.android.ui.history.HistoryDestination

class HistoryNavGraphBuilder(val builder: NavGraphBuilder) {
    fun build() {
        with(builder) {
            navigation(
                startDestination = HistoryDestination.value,
                route = HistoryDestination.graphRoute,
            ) {
                // NOTE : 取引履歴内で遷移するDestinationを追加していく
                HistoryDestination.createNode(this)
            }
        }
    }
}