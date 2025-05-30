package com.thesetox.sync

import com.thesetox.domain.FetchCurrencyRatesUseCase
import com.thesetox.domain.SyncRepository
import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse
import kotlinx.serialization.json.Json
import java.security.MessageDigest

class SyncUseCase(
    private val fetchCurrencyRates: FetchCurrencyRatesUseCase,
    private val syncRepository: SyncRepository,
) {
    suspend operator fun invoke(): String {
        return when (val result = fetchCurrencyRates()) {
            is ApiResult.Error -> result.message
            is ApiResult.Success<CurrencyRateResponse> -> {
                val hash = result.data.convertToMd5()
                val isSyncNeeded = isSyncNeeded(hash)
                // TODO save to database when
                return if (isSyncNeeded) "Sync Completed" else "Sync not needed"
            }
        }
    }

    private fun CurrencyRateResponse.convertToMd5(): String {
        val json = Json.encodeToString(CurrencyRateResponse.serializer(), this)
        return json.md5()
    }

    private suspend fun isSyncNeeded(hash: String): Boolean {
        return hash != syncRepository.getCurrencyRateHash()
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(this.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }
}
