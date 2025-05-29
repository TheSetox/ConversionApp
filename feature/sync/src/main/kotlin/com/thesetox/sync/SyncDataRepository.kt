package com.thesetox.sync

import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateApi
import com.thesetox.network.CurrencyRateResponse

class SyncDataRepository(private val api: CurrencyRateApi) : SyncRepository {
    override suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse> {
        return api.fetchCurrencyRates()
    }

    override suspend fun getCurrencyRateHash(): String {
        TODO("Not yet implemented")
    }

    override suspend fun saveCurrencyRates() {
        TODO("Not yet implemented")
    }

    override suspend fun saveCurrencyRateHash(hash: String) {
        TODO("Not yet implemented")
    }
}
