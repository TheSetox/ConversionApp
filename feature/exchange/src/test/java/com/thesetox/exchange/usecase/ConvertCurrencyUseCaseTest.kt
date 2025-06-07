package com.thesetox.exchange.usecase

import com.thesetox.database.CurrencyRateEntity
import com.thesetox.exchange.repository.ExchangeRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import java.util.Locale

class ConvertCurrencyUseCaseTest {
    private lateinit var repository: ExchangeRepository
    private lateinit var convertCurrency: ConvertCurrencyUseCase

    @Before
    fun setUp() {
        Locale.setDefault(Locale.US)
        repository = mock()
        convertCurrency = ConvertCurrencyUseCase(repository)
    }

    @Test
    fun `returns formatted zero when amount is not valid`() =
        runTest {
            // Act
            val result = convertCurrency("abc", "USD", "EUR")

            // Assert
            assertEquals("0.00", result)
        }

    @Test
    fun `repository not queried when amount is invalid`() =
        runTest {
            // Act
            convertCurrency("abc", "USD", "EUR")

            // Assert
            verifyNoInteractions(repository)
        }

    @Test
    fun `returns converted amount using repository rates`() =
        runTest {
            // Arrange
            whenever(repository.getCurrencyRate("USD")).thenReturn(CurrencyRateEntity("USD", 1.2))
            whenever(repository.getCurrencyRate("EUR")).thenReturn(CurrencyRateEntity("EUR", 1.0))

            // Act
            val result = convertCurrency("10", "USD", "EUR")

            // Assert
            assertEquals("12.00", result)
        }

    @Test
    fun `queries rate for target currency`() =
        runTest {
            // Arrange
            whenever(repository.getCurrencyRate("USD")).thenReturn(CurrencyRateEntity("USD", 1.2))
            whenever(repository.getCurrencyRate("EUR")).thenReturn(CurrencyRateEntity("EUR", 1.0))

            // Act
            convertCurrency("10", "USD", "EUR")

            // Assert
            verify(repository).getCurrencyRate("USD")
        }

    @Test
    fun `queries rate for source currency`() =
        runTest {
            // Arrange
            whenever(repository.getCurrencyRate("USD")).thenReturn(CurrencyRateEntity("USD", 1.2))
            whenever(repository.getCurrencyRate("EUR")).thenReturn(CurrencyRateEntity("EUR", 1.0))

            // Act
            convertCurrency("10", "USD", "EUR")

            // Assert
            verify(repository).getCurrencyRate("EUR")
        }
}
