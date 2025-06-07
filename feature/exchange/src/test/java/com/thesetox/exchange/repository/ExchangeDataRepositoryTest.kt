package com.thesetox.exchange.repository

import com.thesetox.database.CurrencyRateDao
import com.thesetox.database.CurrencyRateEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ExchangeDataRepositoryTest {
    private lateinit var dao: CurrencyRateDao

    @Before
    fun setUp() {
        dao = mock()
    }

    @Test
    fun `listOfCurrencyRate emits data from dao`() =
        runTest {
            // Arrange
            val expected = listOf(CurrencyRateEntity("USD", 1.5))
            whenever(dao.getCurrencyRateList()).thenReturn(flowOf(expected))
            val repository = ExchangeDataRepository(dao)

            // Act
            val result = repository.listOfCurrencyRate.first()

            // Assert
            assertEquals(expected, result)
            verify(dao).getCurrencyRateList()
        }

    @Test
    fun `getCurrencyRate returns rate when exists`() =
        runTest {
            // Arrange
            val expected = CurrencyRateEntity("USD", 1.5)
            whenever(dao.getRate("USD")).thenReturn(expected)
            val repository = ExchangeDataRepository(dao)

            // Act
            val result = repository.getCurrencyRate("USD")

            // Assert
            assertEquals(expected, result)
            verify(dao).getRate("USD")
        }

    @Test
    fun `getCurrencyRate returns default when missing`() =
        runTest {
            // Arrange
            whenever(dao.getRate("GBP")).thenReturn(null)
            val repository = ExchangeDataRepository(dao)

            // Act
            val result = repository.getCurrencyRate("GBP")

            // Assert
            assertEquals(CurrencyRateEntity(), result)
            verify(dao).getRate("GBP")
        }
}
