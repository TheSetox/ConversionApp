package com.thesetox.sync.usecase

import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse

class SyncUseCase(
    private val syncApi: SyncApiUseCase,
) {
    suspend operator fun invoke(): String {
        return when (val result = syncApi()) {
            is ApiResult.Error -> result.message
            is ApiResult.Success<CurrencyRateResponse> -> "sync complete"
        }
    }
}
