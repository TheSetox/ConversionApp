package com.thesetox.databse

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyRateEntity::class], version = 1)
abstract class ConversionAppDatabase : RoomDatabase() {
    abstract fun currencyRateDao(): CurrencyRateDao
}
