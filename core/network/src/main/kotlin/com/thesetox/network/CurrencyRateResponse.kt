package com.thesetox.network

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyRateResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
)
