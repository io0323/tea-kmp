package com.io.tea.android

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.datatransport.BuildConfig
import com.io.tea.android.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initKoin()
        appContext = applicationContext
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initKoin(): KoinApplication {
        return startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MainApplication)
            modules(KoinModules)
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}
