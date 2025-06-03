package com.thesetox.exchange.model

sealed class ExchangeEffect {
    data class ShowToastMessage(val message: String) : ExchangeEffect()

    data class ShowDialog(val message: String) : ExchangeEffect()
}
