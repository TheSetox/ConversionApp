package com.thesetox.comission

/**
 * Encapsulates the commission rules for currency exchanges.
 *
 * The configuration supports several strategies for waiving or
 * applying commission depending on the number of conversions and
 * the exchanged amounts. Typical usage:
 *
 * ```
 * CommissionConfig()
 *     .freeConversionsAtStart(3)
 *     .makeEveryConversionFree(10)
 *     .freeIfAmountEquals(15.0, "EUR")
 *     .applyCommissionRateAfterFreeConversions(0.01)
 * ```
 */

class CommissionConfig {
    /** Number of conversions that are free when the app starts. */
    var freeConversionsAtStartCount: Int = 0

    /** Interval at which every nth conversion is free. */
    var makeEveryConversionFreeInterval: Int? = null

    /** Pair of currency and amount for which the commission should be waived. */
    var exactAmountFreeCondition: Pair<String, Double>? = null

    /** Commission rate applied once free conversions are exhausted. */
    var commissionRateAfterFreeConversions: Double? = null

    /**
     * Sets how many initial conversions are performed without commission.
     *
     * @param count number of conversions to waive at the beginning
     */
    fun freeConversionsAtStart(count: Int) =
        apply {
            freeConversionsAtStartCount = count
        }

    /**
     * Makes every [everyConversionNumber]th conversion free of commission.
     */
    fun makeEveryConversionFree(everyConversionNumber: Int) =
        apply {
            makeEveryConversionFreeInterval = everyConversionNumber
        }

    /**
     * Waives commission when the user sells [amount] of the specified [currency].
     */
    fun freeIfAmountEquals(
        amount: Double,
        currency: String,
    ) = apply {
        exactAmountFreeCondition = currency to amount
    }

    /**
     * Applies a commission [rate] after all free conversions have been used.
     */
    fun applyCommissionRateAfterFreeConversions(rate: Double) =
        apply {
            commissionRateAfterFreeConversions = rate
        }

    /**
     * Calculates the commission for a conversion.
     *
     * @param sellAmount amount being sold by the user
     * @param currency currency of the selling amount
     * @param conversionCount how many conversions the user has performed so far
     * @return the commission fee to apply
     */
    fun calculateCommission(
        sellAmount: Double,
        currency: String,
        conversionCount: Int,
    ): Double {
        return when {
            conversionCount <= freeConversionsAtStartCount -> 0.0

            makeEveryConversionFreeInterval != null &&
                conversionCount % makeEveryConversionFreeInterval!! == 0 -> 0.0

            exactAmountFreeCondition?.let { (targetCurrency, targetAmount) ->
                currency == targetCurrency && sellAmount == targetAmount
            } == true -> 0.0

            commissionRateAfterFreeConversions != null ->
                sellAmount * commissionRateAfterFreeConversions!!

            else -> 0.0
        }
    }
}
