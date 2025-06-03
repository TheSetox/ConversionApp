package com.thesetox.conversionapp

import android.app.Application
import com.thesetox.balance.balanceModule
import com.thesetox.comission.commissionModule
import com.thesetox.databse.databaseModule
import com.thesetox.datastore.dataStoreModule
import com.thesetox.exchange.exchangeModule
import com.thesetox.network.networkModule
import com.thesetox.sync.syncModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ConversionApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ConversionApp)
            modules(listOfModule)
        }
    }

    private val listOfModule =
        listOf(
            // core modules
            networkModule,
            dataStoreModule,
            databaseModule,
            // feature modules
            syncModule,
            exchangeModule,
            balanceModule,
            commissionModule,
        )
}
