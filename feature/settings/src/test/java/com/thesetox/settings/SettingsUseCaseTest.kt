package com.thesetox.settings

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SettingsUseCaseTest {
    private val repository: SettingsRepository = mock()

    @Test
    fun `saving preference updates repository`() =
        runTest {
            // Arrange
            val setUseCase = SetDarkThemeEnabledUseCase(repository)

            // Act
            setUseCase(true)

            // Assert
            verify(repository).setDarkThemeEnabled(true)
        }

    @Test
    fun `getting preference delegates to repository`() =
        runTest {
            // Arrange
            whenever(repository.isDarkThemeEnabled()).thenReturn(true)
            val getUseCase = GetDarkThemeEnabledUseCase(repository)

            // Act
            getUseCase()

            // Assert
            verify(repository).isDarkThemeEnabled()
        }
}
