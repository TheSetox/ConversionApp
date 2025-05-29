package com.thesetox.sync.usecase

import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse
import com.thesetox.sync.SyncRepository

class SyncApiUseCase(private val repository: SyncRepository) {
    suspend operator fun invoke(): ApiResult<CurrencyRateResponse> {
        return repository.fetchCurrencyRates()
    }
}
