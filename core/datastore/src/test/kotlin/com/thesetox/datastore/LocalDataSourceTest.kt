package com.thesetox.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class LocalDataSourceTest {
    private val dataStore: DataStore<Preferences> = mock()
    private val localDataSource = LocalDataSource(dataStore)

    @Test
    fun `getCurrencyRateHash uses dataStore`() =
        runTest {
            // Arrange
            whenever(dataStore.data).thenReturn(flowOf(emptyPreferences()))

            // Act
            localDataSource.getCurrencyRateHash()

            // Assert
            verify(dataStore).data
        }

    @Test
    fun `saveCurrencyRateHash writes to dataStore`() =
        runTest {
            // Arrange
            whenever(dataStore.edit(any()))
                .thenReturn(emptyPreferences())

            // Act
            localDataSource.saveCurrencyRateHash("abc")

            // Assert
            verify(dataStore).edit(any())
        }
}
