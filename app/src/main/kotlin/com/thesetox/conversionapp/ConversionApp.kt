package com.thesetox.conversionapp

import android.app.Application
import com.thesetox.domain.domainModule
import com.thesetox.network.networkModule
import com.thesetox.sync.syncModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConversionApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ConversionApp)
            modules(listOf(networkModule, domainModule, syncModule))
        }
    }
}
