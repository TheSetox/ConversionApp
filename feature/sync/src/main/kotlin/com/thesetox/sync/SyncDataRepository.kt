package com.thesetox.sync

import com.thesetox.datastore.AppDataStore
import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateApi
import com.thesetox.network.CurrencyRateResponse

class SyncDataRepository(
    private val api: CurrencyRateApi,
    private val dataStore: AppDataStore,
) : SyncRepository {
    override suspend fun fetchCurrencyRates(): ApiResult<CurrencyRateResponse> {
        return api.fetchCurrencyRates()
    }

    override suspend fun getCurrencyRateHash(): String {
        return dataStore.getCurrencyRateHash()
    }

    override suspend fun saveCurrencyRates() {
        TODO("Not yet implemented")
    }

    override suspend fun saveCurrencyRateHash(hash: String) {
        dataStore.saveCurrencyRateHash(hash)
    }
}
