package com.thesetox.exchange

import com.thesetox.databse.CurrencyRateDao
import com.thesetox.databse.CurrencyRateEntity
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
