package com.thesetox.databse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_rate")
data class CurrencyRateEntity(
    @PrimaryKey val currencyCode: String = "",
    val rate: Double = 0.0,
    val baseCurrency: String = "EUR",
    val date: String = "",
)
