package com.thesetox.exchange.model

sealed class ExchangeResult {
    data object Success : ExchangeResult()

    data class Error(val message: String) : ExchangeResult()
}

data class ExchangeResultWithUpdatedBalances(
    val result: ExchangeResult,
    val updatedBalances: List<Balance>,
)
