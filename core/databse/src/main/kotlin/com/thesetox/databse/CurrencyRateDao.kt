package com.thesetox.databse

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CurrencyRateDao {
    @Query("SELECT * FROM currency_rate")
    suspend fun getCurrencyRateList(): List<CurrencyRateEntity>

    @Query("SELECT * FROM currency_rate WHERE currencyCode = :code")
    suspend fun getRate(code: String): CurrencyRateEntity?

    @Upsert
    suspend fun insertRates(rates: List<CurrencyRateEntity>)

    @Query("DELETE FROM currency_rate")
    suspend fun clearRates()
}
