package com.thesetox.exchange

import com.thesetox.databse.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    val listOfCurrencyRate: Flow<List<CurrencyRateEntity>>

    suspend fun getCurrencyRate(code: String): CurrencyRateEntity
}
