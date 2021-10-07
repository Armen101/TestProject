package com.test.testproject

import android.app.Application
import com.test.testproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    private val diModules = listOf(
        viewModelModule,
        networkModule,
        repoModule,
        dbModule
    )

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(applicationContext)
            modules(diModules)
        }
    }

}