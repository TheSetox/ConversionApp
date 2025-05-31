package com.thesetox.sync

import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse

interface SyncRepository {
    suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse>

    suspend fun getCurrencyRateHash(): String

    suspend fun saveCurrencyRates()

    suspend fun saveCurrencyRateHash(hash: String)
}
