package com.thesetox.network

import kotlinx.serialization.Serializable

/**
 * Model of the currency rates received from the remote service.
 *
 * @property base the currency used as the reference for all rates
 * @property date the date when the rates were published
 * @property rates a map of currency codes to their relative exchange rate
 */

@Serializable
data class CurrencyRateResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
)
