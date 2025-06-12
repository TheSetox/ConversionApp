package com.thesetox.comission

/**
 * Calculates the commission fee for each currency conversion.
 *
 * This use case keeps track of how many conversions have been made and
 * applies rules from [CommissionConfig] to determine the fee for a given
 * transaction. By default, the first five conversions are free and a 0.7%
 * commission is charged afterwards. The configuration can be extended with
 * additional rules if needed.
 */
class GetCommissionUseCase {
    private var conversionCount = 0

    operator fun invoke(
        sellAmount: Double,
        currency: String,
    ): Double {
        conversionCount++

        val config =
            CommissionConfig()
                .freeConversionsAtStart(5)
                // TODO sample of flexibility by adding rules.
                // .makeEveryConversionFree(10)
                // .freeIfAmountEquals(200.0, "EUR")
                .applyCommissionRateAfterFreeConversions(0.007)

        return config.calculateCommission(sellAmount, currency, conversionCount)
    }
}
