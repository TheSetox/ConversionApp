package com.thesetox.exchange.repository

import com.thesetox.database.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository exposing access to currency exchange rates stored locally.
 */
interface ExchangeRepository {
    /**
     * Continuous stream of all available currency rates.
     */
    val listOfCurrencyRate: Flow<List<CurrencyRateEntity>>

    /**
     * Returns the rate information for the given currency code.
     *
     * @param code ISO currency code (e.g. "EUR").
     */
    suspend fun getCurrencyRate(code: String): CurrencyRateEntity
}
