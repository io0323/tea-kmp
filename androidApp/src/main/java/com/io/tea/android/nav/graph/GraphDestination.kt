package com.io.tea.android.nav.graph

import com.io.tea.android.nav.Destination

abstract class GraphDestination(value: String) : Destination(value) {

    val graphRoute: String
        get() = "graph/$value"
}
