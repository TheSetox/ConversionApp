package com.thesetox.sync

import com.thesetox.database.CurrencyRateDao
import com.thesetox.database.CurrencyRateEntity
import com.thesetox.datastore.AppDataStore
import com.thesetox.network.ApiResult
import com.thesetox.network.CurrencyRateApi
import com.thesetox.network.CurrencyRateResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

// Tests in this suite follow the Arrange-Act-Assert (AAA) pattern.
class SyncDataRepositoryTest {
    @Test
    fun `fetchCurrencyRates delegates to api`() =
        runTest {
            // Arrange
            val apiResult = ApiResult.Success(CurrencyRateResponse("EUR", "", emptyMap()))
            val api = mock<CurrencyRateApi> { onBlocking { fetchCurrencyRates() } doReturn apiResult }
            val repository = SyncDataRepository(api, mock(), mock())

            // Act
            repository.fetchCurrencyRates()

            // Assert
            verify(api).fetchCurrencyRates()
        }

    @Test
    fun `getCurrencyRateHash delegates to datastore`() =
        runTest {
            // Arrange
            val dataStore = mock<AppDataStore> { onBlocking { getCurrencyRateHash() } doReturn "hash" }
            val repository = SyncDataRepository(mock(), dataStore, mock())

            // Act
            repository.getCurrencyRateHash()

            // Assert
            verify(dataStore).getCurrencyRateHash()
        }

    @Test
    fun `saveCurrencyRates delegates to dao`() =
        runTest {
            // Arrange
            val dao = mock<CurrencyRateDao>()
            val repository = SyncDataRepository(mock(), mock(), dao)
            val list = emptyList<CurrencyRateEntity>()

            // Act
            repository.saveCurrencyRates(list)

            // Assert
            verify(dao).insertRates(list)
        }

    @Test
    fun `saveCurrencyRateHash delegates to datastore`() =
        runTest {
            // Arrange
            val dataStore = mock<AppDataStore>()
            val repository = SyncDataRepository(mock(), dataStore, mock())

            // Act
            repository.saveCurrencyRateHash("hash")

            // Assert
            verify(dataStore).saveCurrencyRateHash("hash")
        }

    @Test
    fun `clearRates delegates to dao`() =
        runTest {
            // Arrange
            val dao = mock<CurrencyRateDao>()
            val repository = SyncDataRepository(mock(), mock(), dao)

            // Act
            repository.clearRates()

            // Assert
            verify(dao).clearRates()
        }
}
