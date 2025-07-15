package com.io.tea.android.ui.webview.model

import android.net.Uri

// TODO : Domain
internal const val Domain = "https://www.google.com"

internal sealed class WebDomainModel {

    data object Init : WebDomainModel()

    data class Loaded(
        val url: String,
        val authSession: Boolean,
    ) : WebDomainModel() {
        val isDomain: Boolean = Uri.parse(url).host?.endsWith(Domain) == true
    }
}
