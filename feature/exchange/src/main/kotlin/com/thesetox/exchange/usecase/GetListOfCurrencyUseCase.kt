package com.thesetox.exchange.usecase

import com.thesetox.exchange.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetListOfCurrencyUseCase(private val repository: ExchangeRepository) {
    operator fun invoke(): Flow<List<String>> {
        return repository.listOfCurrencyRate.map { list -> list.map { it.currencyCode } }
    }
}
