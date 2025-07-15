package com.io.tea.android.nav.graph.builder

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.io.tea.android.ui.region.detail.MyTeaDetailDestination
import com.io.tea.android.ui.region.home.MyTeaHomeDestination
import com.io.tea.android.ui.region.list.MyTeaListDestination

class MyTeaNavGraphBuilder(val builder: NavGraphBuilder) {
    fun build() {
        with(builder) {
            navigation(
                startDestination = MyTeaHomeDestination.value,
                route = MyTeaHomeDestination.graphRoute,
            ) {
                MyTeaHomeDestination.createNode(this)
                MyTeaListDestination.createNode(this)
                MyTeaDetailDestination.createNode(this)
            }
        }
    }
}