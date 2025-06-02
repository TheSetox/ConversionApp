package com.thesetox.exchange.usecase

import com.thesetox.databse.CurrencyRateEntity
import com.thesetox.exchange.ExchangeRepository
import kotlinx.coroutines.flow.Flow

class GetListOfCurrencyRateUseCase(private val repository: ExchangeRepository) {
    operator fun invoke(): Flow<List<CurrencyRateEntity>> {
        return repository.listOfCurrencyRate
    }
}
