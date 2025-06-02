package com.thesetox.exchange.usecase

import com.thesetox.exchange.ExchangeRepository
import java.util.Locale

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
