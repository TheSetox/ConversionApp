package com.thesetox.balance

class GetDefaultBalanceUseCase {
    operator fun invoke(): List<Balance> {
        return listOf(Balance(code = "EUR", value = 1000.0))
    }
}
