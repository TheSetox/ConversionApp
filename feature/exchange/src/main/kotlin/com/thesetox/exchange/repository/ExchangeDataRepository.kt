package com.thesetox.exchange.repository

import com.thesetox.database.CurrencyRateDao
import com.thesetox.database.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

class ExchangeDataRepository(
    private val currencyRateDao: CurrencyRateDao,
) : ExchangeRepository {
    override val listOfCurrencyRate: Flow<List<CurrencyRateEntity>> =
        currencyRateDao.getCurrencyRateList()

    override suspend fun getCurrencyRate(code: String): CurrencyRateEntity {
        return currencyRateDao.getRate(code) ?: CurrencyRateEntity()
    }
}
