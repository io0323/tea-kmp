package com.io.tea.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri

// actualキーワードを使って期待される関数を実装
actual fun getPackageName(): String {
    // Contextは呼び出し元から渡されると仮定
    // 必要であれば、Contextを引数として渡すこともできます
    return ContextProvider.context.packageName
}

fun getDrawableFromUri(context: Context, uri: Uri): Drawable? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        Drawable.createFromStream(inputStream, uri.toString())
    } catch (e: Exception) {
        null
    }
}

@SuppressLint("StaticFieldLeak")
object ContextProvider {
    lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }
}