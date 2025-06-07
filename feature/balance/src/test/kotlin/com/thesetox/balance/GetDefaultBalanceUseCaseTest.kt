package com.thesetox.balance

import org.junit.Assert.assertEquals
import org.junit.Test

class GetDefaultBalanceUseCaseTest {
    @Test
    fun `invoke returns EUR balance with initial amount`() {
        // Arrange
        val getDefaultBalance = GetDefaultBalanceUseCase()

        // Act
        val result = getDefaultBalance()

        // Assert
        assertEquals(listOf(Balance(code = "EUR", value = 1000.0)), result)
    }
}
