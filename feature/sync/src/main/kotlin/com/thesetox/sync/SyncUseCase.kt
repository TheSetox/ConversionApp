package com.thesetox.sync

import com.thesetox.databse.CurrencyRateEntity
import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse
import kotlinx.serialization.json.Json
import java.security.MessageDigest

class SyncUseCase(private val repository: SyncRepository) {
    suspend operator fun invoke(): String {
        return when (val response = repository.fetchCurrencyRates()) {
            is ApiResult.Error -> response.message
            is ApiResult.Success<CurrencyRateResponse> -> {
                val result = response.data
                val hash = result.convertToMd5()
                val isSyncNeeded = isSyncNeeded(hash)
                return if (isSyncNeeded) {
                    repository.saveCurrencyRateHash(hash)
                    val list = result.toListOfCurrencyRateEntity()
                    repository.clearRates()
                    repository.saveCurrencyRates(list)
                    SYNC_COMPLETE
                } else {
                    SYNC_NOT_NEEDED
                }
            }
        }
    }

    private fun CurrencyRateResponse.convertToMd5(): String {
        val json = Json.encodeToString(CurrencyRateResponse.serializer(), this)
        return json.md5()
    }

    private suspend fun isSyncNeeded(hash: String): Boolean {
        return hash != repository.getCurrencyRateHash()
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance(MD5_ALGORITHM)
        val digest = md.digest(this.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }

    private fun CurrencyRateResponse.toListOfCurrencyRateEntity(): List<CurrencyRateEntity> {
        return rates.map {
            CurrencyRateEntity(
                currencyCode = it.key,
                rate = it.value,
                baseCurrency = base,
                date = date,
            )
        }
    }

    companion object {
        private const val MD5_ALGORITHM = "MD5"
        private const val SYNC_COMPLETE = "Sync Completed"
        private const val SYNC_NOT_NEEDED = "Sync Not Needed"
    }
}
