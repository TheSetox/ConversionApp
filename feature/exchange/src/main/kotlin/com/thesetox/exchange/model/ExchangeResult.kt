package com.thesetox.exchange.model

import com.thesetox.balance.Balance

sealed class ExchangeResult {
    data class Success(val commissionFee: String) : ExchangeResult()

    data class Error(val message: String) : ExchangeResult()
}

data class ExchangeResultWithUpdatedBalances(
    val result: ExchangeResult,
    val updatedBalances: List<Balance>,
)
