package com.thesetox.transaction

import org.junit.Assert.assertEquals
import org.junit.Test

class AddTransactionUseCaseTest {
    @Test
    fun `transaction is added to repository`() {
        // Arrange
        val repository = TransactionDataRepository()
        val addTransaction = AddTransactionUseCase(repository)
        val getTransactions = GetTransactionsUseCase(repository)
        val transaction = Transaction(
            sellCurrency = "EUR",
            receiveCurrency = "USD",
            sellAmount = 10.0,
            receiveAmount = 12.0,
            commission = 0.07,
        )

        // Act
        addTransaction(transaction)
        val result = getTransactions()

        // Assert
        assertEquals(listOf(transaction), result)
    }
}
