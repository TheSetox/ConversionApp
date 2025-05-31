package com.thesetox.exchange

import com.thesetox.databse.CurrencyRateDao
import com.thesetox.databse.CurrencyRateEntity
import kotlinx.coroutines.flow.Flow

class ExchangeDataRepository(
    currencyRateDao: CurrencyRateDao,
) : ExchangeRepository {
    override val listOfCurrencyRate: Flow<List<CurrencyRateEntity>> =
        currencyRateDao.getCurrencyRateList()
}
