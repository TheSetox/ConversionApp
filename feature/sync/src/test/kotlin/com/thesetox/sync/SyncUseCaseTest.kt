package com.thesetox.sync

import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateResponse
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.security.MessageDigest

class SyncUseCaseTest {
    private val repository: SyncRepository = mock()
    private val sync = SyncUseCase(repository)

    @Test
    fun `returns error message when fetch fails`() =
        runTest {
            // Arrange
            whenever(repository.fetchCurrencyRates()).thenReturn(ApiResult.Error("Network Error"))

            // Act
            val message = sync()

            // Assert
            assertEquals("Network Error", message)
        }

    @Test
    fun `saves new hash when rates changed`() =
        runTest {
            // Arrange
            val response =
                CurrencyRateResponse(
                    base = "EUR",
                    date = "2024-06-01",
                    rates = mapOf("USD" to 1.2),
                )
            whenever(repository.fetchCurrencyRates()).thenReturn(ApiResult.Success(response))
            whenever(repository.getCurrencyRateHash()).thenReturn("old")
            val expectedHash = md5(Json.encodeToString(CurrencyRateResponse.serializer(), response))

            // Act
            sync()

            // Assert
            verify(repository).saveCurrencyRateHash(expectedHash)
        }

    @Test
    fun `does not sync when hash matches`() =
        runTest {
            // Arrange
            val response =
                CurrencyRateResponse(
                    base = "EUR",
                    date = "2024-06-01",
                    rates = mapOf("USD" to 1.2),
                )
            val hash = md5(Json.encodeToString(CurrencyRateResponse.serializer(), response))
            whenever(repository.fetchCurrencyRates()).thenReturn(ApiResult.Success(response))
            whenever(repository.getCurrencyRateHash()).thenReturn(hash)

            // Act
            sync()

            // Assert
            verify(repository, never()).clearRates()
        }

    private fun md5(text: String): String {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(text.toByteArray()).joinToString("") { "%02x".format(it) }
    }
}
