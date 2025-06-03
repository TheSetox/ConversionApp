package com.thesetox.exchange.model

import com.thesetox.balance.Balance

data class ExchangeState(
    val sellAmount: String = "0",
    val receiveAmount: String = "0",
    val selectedSellCurrency: String = "EUR",
    val selectedReceiveCurrency: String = "USD",
    val listOfBalance: List<Balance> = emptyList(),
    val listOfCurrencies: List<String> = emptyList(),
)
