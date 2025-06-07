package com.thesetox.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule =
    module {
        single {
            Room.databaseBuilder(
                androidContext(),
                ConversionAppDatabase::class.java,
                "conversion.db",
            )
                .build()
        }

        single { get<ConversionAppDatabase>().currencyRateDao() }
    }
