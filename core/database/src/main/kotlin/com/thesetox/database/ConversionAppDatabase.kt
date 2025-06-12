package com.thesetox.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CurrencyRateEntity::class, BalanceEntity::class],
    version = 2,
)
abstract class ConversionAppDatabase : RoomDatabase() {
    abstract fun currencyRateDao(): CurrencyRateDao
    abstract fun balanceDao(): BalanceDao
}
