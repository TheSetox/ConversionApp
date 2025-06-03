package com.thesetox.comission

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
