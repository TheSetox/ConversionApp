package com.thesetox.exchange.model

class CommissionConfig {
    var freeConversionsAtStartCount: Int = 0
    var makeEveryConversionFreeInterval: Int? = null
    var exactAmountFreeCondition: Pair<String, Double>? = null
    var commissionRateAfterFreeConversions: Double? = null

    fun freeConversionsAtStart(count: Int) =
        apply {
            freeConversionsAtStartCount = count
        }

    fun makeEveryConversionFree(everyConversionNumber: Int) =
        apply {
            makeEveryConversionFreeInterval = everyConversionNumber
        }

    fun freeIfAmountEquals(
        amount: Double,
        currency: String,
    ) = apply {
        exactAmountFreeCondition = currency to amount
    }

    fun applyCommissionRateAfterFreeConversions(rate: Double) =
        apply {
            commissionRateAfterFreeConversions = rate
        }

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
