package com.thesetox.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyRateDao {
    @Query("SELECT * FROM currency_rate")
    fun getCurrencyRateList(): Flow<List<CurrencyRateEntity>>

    @Query("SELECT * FROM currency_rate WHERE currencyCode = :code")
    suspend fun getRate(code: String): CurrencyRateEntity?

    @Upsert
    suspend fun insertRates(rates: List<CurrencyRateEntity>)

    @Query("DELETE FROM currency_rate")
    suspend fun clearRates()
}
