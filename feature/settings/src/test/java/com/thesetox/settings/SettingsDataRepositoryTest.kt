package com.thesetox.settings

import com.thesetox.datastore.AppDataStore
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

// Tests verifying the repository delegates to the underlying datastore.
class SettingsDataRepositoryTest {
    private val dataStore: AppDataStore = mock()
    private val repository = SettingsDataRepository(dataStore)

    @Test
    fun `isDarkThemeEnabled delegates to datastore`() =
        runTest {
            // Arrange
            whenever(dataStore.isDarkThemeEnabled()).thenReturn(false)

            // Act
            repository.isDarkThemeEnabled()

            // Assert
            verify(dataStore).isDarkThemeEnabled()
        }

    @Test
    fun `setDarkThemeEnabled delegates to datastore`() =
        runTest {
            // Act
            repository.setDarkThemeEnabled(true)

            // Assert
            verify(dataStore).setDarkThemeEnabled(true)
        }
}
