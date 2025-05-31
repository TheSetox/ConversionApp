package com.thesetox.exchange

import com.thesetox.databse.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

class GetListOfCurrencyRateUseCase(private val repository: ExchangeRepository) {
    operator fun invoke(): Flow<List<CurrencyRateEntity>> {
        return repository.listOfCurrencyRate
    }
}
