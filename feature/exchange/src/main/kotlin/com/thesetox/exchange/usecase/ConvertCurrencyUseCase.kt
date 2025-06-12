package com.thesetox.exchange.usecase

import com.thesetox.exchange.repository.ExchangeRepository
import java.util.Locale

/**
 * Converts a given amount from one currency to another.
 *
 * The use case obtains the currency rates from [ExchangeRepository] and formats
 * the result with two decimal digits using the current [Locale]. If the passed
 * [amount] cannot be parsed, it returns `"0.00"`.
 */
class ConvertCurrencyUseCase(private val repository: ExchangeRepository) {
    suspend operator fun invoke(
        amount: String,
        toCurrency: String,
        fromCurrency: String,
    ): String {
        val parsedAmount = amount.toDoubleOrNull() ?: return "0.00"
        val newCurrencyRate = repository.getCurrencyRate(toCurrency).rate
        val oldCurrencyRate = repository.getCurrencyRate(fromCurrency).rate
        val result = parsedAmount / oldCurrencyRate * newCurrencyRate
        return String.format(Locale.getDefault(), "%.2f", result)
    }
}
