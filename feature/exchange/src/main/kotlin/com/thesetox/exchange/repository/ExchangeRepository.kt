package com.thesetox.exchange.repository

import com.thesetox.database.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    val listOfCurrencyRate: Flow<List<CurrencyRateEntity>>

    suspend fun getCurrencyRate(code: String): CurrencyRateEntity
}
