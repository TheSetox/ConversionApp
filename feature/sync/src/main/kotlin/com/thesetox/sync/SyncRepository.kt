package com.thesetox.sync

import com.thesetox.database.CurrencyRateEntity
import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse

/**
 * Repository interface used by the sync feature to interact with the data layer.
 *
 * Implementations are responsible for fetching currency rates from the remote
 * API, persisting them locally and storing a hash of the latest response so that
 * changes can be detected on subsequent syncs.
 */
interface SyncRepository {

    /**
     * Fetch the latest exchange rates from the remote source.
     *
     * @return [ApiResult] wrapping the service response or an error.
     */
    suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse>

    /**
     * Retrieve the currently stored hash representing the last synced rates.
     */
    suspend fun getCurrencyRateHash(): String

    /**
     * Persist a list of currency rates in the local database.
     *
     * @param list the rates to be saved.
     */
    suspend fun saveCurrencyRates(list: List<CurrencyRateEntity>)

    /**
     * Store a hash that identifies the last downloaded set of rates.
     *
     * @param hash the computed hash value.
     */
    suspend fun saveCurrencyRateHash(hash: String)

    /**
     * Remove all stored currency rates from the local database.
     */
    suspend fun clearRates()
}
