package com.thesetox.comission

import org.junit.Assert.assertEquals
import org.junit.Test

class GetCommissionUseCaseTest {
    @Test
    fun `first five conversions have zero commission`() {
        // Arrange
        val getCommission = GetCommissionUseCase()

        // Act
        val commissions = List(5) { getCommission(100.0, "EUR") }

        // Assert
        commissions.forEach { assertEquals(0.0, it, 0.0001) }
    }

    @Test
    fun `commission applied after free conversions`() {
        // Arrange
        val getCommission = GetCommissionUseCase()

        // Act
        repeat(5) { getCommission(100.0, "EUR") }
        val commission = getCommission(100.0, "EUR")

        // Assert
        assertEquals(100.0 * 0.007, commission, 0.0001)
    }
}
