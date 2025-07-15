package com.io.tea.android.util

import android.content.Context

object ImageUtil {
    fun getDrawablePath(context: Context, drawablePath: String): String {
        return "android.resource://${context.packageName}/${drawablePath}"
    }
}