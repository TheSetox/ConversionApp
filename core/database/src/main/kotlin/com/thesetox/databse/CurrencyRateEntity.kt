package com.thesetox.databse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rate")
data class CurrencyRateEntity(
    @PrimaryKey val currencyCode: String,
    val rate: Double,
    val baseCurrency: String,
    val date: String,
)
