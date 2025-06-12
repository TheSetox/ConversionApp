package com.thesetox.balance

/**
 * Represents a single entry in the user's wallet.
 *
 * @property code ISO currency code such as "EUR" or "USD".
 * @property value Numeric balance amount for the given currency.
 */
data class Balance(
    val code: String,
    val value: Double,
)
