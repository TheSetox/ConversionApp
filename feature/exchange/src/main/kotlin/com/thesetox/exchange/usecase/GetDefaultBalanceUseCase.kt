package com.thesetox.exchange.usecase

import com.thesetox.exchange.model.Balance

class GetDefaultBalanceUseCase {
    operator fun invoke(): List<Balance> {
        return listOf(Balance(code = "EUR", value = 1000.0))
    }
}
