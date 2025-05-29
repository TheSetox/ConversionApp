package com.thesetox.domain

import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse

class FetchCurrencyRatesUseCase(private val repository: SyncRepository) {
    suspend operator fun invoke(): ApiResult<CurrencyRateResponse> {
        return repository.fetchCurrencyRates()
    }
}
