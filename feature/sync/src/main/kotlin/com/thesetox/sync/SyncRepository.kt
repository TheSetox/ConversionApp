package com.thesetox.sync

import com.thesetox.databse.CurrencyRateEntity
import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse

interface SyncRepository {
    suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse>

    suspend fun getCurrencyRateHash(): String

    suspend fun saveCurrencyRates(list: List<CurrencyRateEntity>)

    suspend fun saveCurrencyRateHash(hash: String)

    suspend fun clearRates()
}
