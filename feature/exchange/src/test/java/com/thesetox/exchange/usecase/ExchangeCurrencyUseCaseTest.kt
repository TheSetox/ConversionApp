package com.thesetox.exchange.usecase

import com.thesetox.balance.Balance
import com.thesetox.comission.GetCommissionUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class ExchangeCurrencyUseCaseTest {
    private lateinit var getCommission: GetCommissionUseCase
    private lateinit var exchangeCurrency: ExchangeCurrencyUseCase

    @Before
    fun setup() {
        getCommission = mock()
        exchangeCurrency = ExchangeCurrencyUseCase(getCommission)
    }

    @Test
    fun `invalid amounts do not calculate commission`() {
        // Arrange
        val sellAmount = "abc"
        val receiveAmount = "10"
        val sellCurrency = "EUR"
        val receiveCurrency = "USD"
        val balances = emptyList<Balance>()

        // Act
        exchangeCurrency(
            sellAmount = sellAmount,
            receiveAmount = receiveAmount,
            selectedSellCurrency = sellCurrency,
            selectedReceiveCurrency = receiveCurrency,
            currentBalances = balances,
        )

        // Assert
        verifyNoInteractions(getCommission)
    }

    @Test
    fun `missing sell balance does not calculate commission`() {
        // Arrange
        val sellAmount = "10"
        val receiveAmount = "12"
        val sellCurrency = "EUR"
        val receiveCurrency = "USD"
        val balances = listOf(Balance("USD", 50.0))

        // Act
        exchangeCurrency(
            sellAmount = sellAmount,
            receiveAmount = receiveAmount,
            selectedSellCurrency = sellCurrency,
            selectedReceiveCurrency = receiveCurrency,
            currentBalances = balances,
        )

        // Assert
        verifyNoInteractions(getCommission)
    }

    @Test
    fun `insufficient balance calculates commission`() {
        // Arrange
        val sellAmount = "10"
        val receiveAmount = "12"
        val sellCurrency = "EUR"
        val receiveCurrency = "USD"
        whenever(getCommission(sellAmount = 10.0, currency = sellCurrency)).thenReturn(0.5)
        val balances = listOf(Balance("EUR", 5.0))

        // Act
        exchangeCurrency(
            sellAmount = sellAmount,
            receiveAmount = receiveAmount,
            selectedSellCurrency = sellCurrency,
            selectedReceiveCurrency = receiveCurrency,
            currentBalances = balances,
        )
        // Assert
        verify(getCommission).invoke(sellAmount = 10.0, currency = sellCurrency)
    }

    @Test
    fun `successful exchange calculates commission`() {
        // Arrange
        val sellAmount = "10"
        val receiveAmount = "12"
        val sellCurrency = "EUR"
        val receiveCurrency = "USD"
        whenever(getCommission(sellAmount = 10.0, currency = sellCurrency)).thenReturn(0.5)
        val balances = listOf(Balance("EUR", 20.0))

        // Act
        exchangeCurrency(
            sellAmount = sellAmount,
            receiveAmount = receiveAmount,
            selectedSellCurrency = sellCurrency,
            selectedReceiveCurrency = receiveCurrency,
            currentBalances = balances,
        )
        // Assert
        verify(getCommission).invoke(sellAmount = 10.0, currency = sellCurrency)
    }
}
