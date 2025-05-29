package com.thesetox.sync

import com.thesetox.domain.FetchCurrencyRatesUseCase
import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse

class SyncUseCase(
    private val syncApi: FetchCurrencyRatesUseCase,
) {
    suspend operator fun invoke(): String {
        return when (val result = syncApi()) {
            is ApiResult.Error -> result.message
            is ApiResult.Success<CurrencyRateResponse> -> "sync complete"
        }
    }
}
