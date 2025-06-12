package com.thesetox.transaction

/**
 * Represents a single currency exchange transaction.
 *
 * @property sellCurrency Currency code being sold.
 * @property receiveCurrency Currency code being received.
 * @property sellAmount Amount sold in [sellCurrency].
 * @property receiveAmount Amount received in [receiveCurrency].
 * @property commission Fee applied to the transaction.
 */
data class Transaction(
    val sellCurrency: String,
    val receiveCurrency: String,
    val sellAmount: Double,
    val receiveAmount: Double,
    val commission: Double,
)
