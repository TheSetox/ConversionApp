package com.thesetox.exchange.ui

data class ExchangeState(
    val sellAmount: String = "0.00",
    val receiveAmount: String = "0.00",
    val selectedSellCurrency: String = "EUR",
    val selectedReceiveCurrency: String = "USD",
    val listOfBalance: List<String> = emptyList(),
)
