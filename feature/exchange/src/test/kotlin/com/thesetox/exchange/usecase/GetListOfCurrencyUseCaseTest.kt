package com.thesetox.exchange.usecase

// Tests are structured using the AAA (Arrange-Act-Assert) pattern.

import com.thesetox.database.CurrencyRateEntity
import com.thesetox.exchange.repository.ExchangeRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetListOfCurrencyUseCaseTest {
    private val repository = mock<ExchangeRepository>()

    @Test
    fun `invoke returns currency codes`() =
        runTest {
            // Arrange
            whenever(repository.listOfCurrencyRate).thenReturn(
                flowOf(
                    listOf(
                        CurrencyRateEntity(currencyCode = "USD"),
                        CurrencyRateEntity(currencyCode = "JPY"),
                    ),
                ),
            )

            val getListOfCurrency = GetListOfCurrencyUseCase(repository)

            // Act
            val result = getListOfCurrency().first()

            // Assert
            assertEquals(listOf("USD", "JPY"), result)
        }

    @Test
    fun `invoke collects list from repository`() =
        runTest {
            // Arrange
            whenever(repository.listOfCurrencyRate).thenReturn(flowOf(emptyList()))
            val getListOfCurrency = GetListOfCurrencyUseCase(repository)

            // Act
            getListOfCurrency().first()

            // Assert
            verify(repository).listOfCurrencyRate
        }
}
