/**
 * Dependency injection definitions for the database layer of the application.
 */
package com.thesetox.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin module that provides the Room database and its DAO dependencies.
 *
 * The module exposes a singleton instance of [ConversionAppDatabase] built with
 * `Room.databaseBuilder` and singleton DAOs ([CurrencyRateDao] and
 * [BalanceDao]) retrieved from the database. These can then be injected
 * throughout the app.
 */
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
        single { get<ConversionAppDatabase>().balanceDao() }
    }
