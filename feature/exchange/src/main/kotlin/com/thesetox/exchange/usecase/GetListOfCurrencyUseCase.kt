package com.thesetox.exchange.usecase

import com.thesetox.exchange.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Use case that emits a list of available currency codes.
 *
 * It observes [ExchangeRepository.listOfCurrencyRate] and converts each
 * [com.thesetox.database.CurrencyRateEntity] to its corresponding currency code.
 */

class GetListOfCurrencyUseCase(private val repository: ExchangeRepository) {
    operator fun invoke(): Flow<List<String>> {
        return repository.listOfCurrencyRate.map { list -> list.map { it.currencyCode } }
    }
}
