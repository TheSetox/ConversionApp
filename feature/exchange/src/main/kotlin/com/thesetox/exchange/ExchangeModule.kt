package com.thesetox.exchange

import com.thesetox.exchange.usecase.ConvertCurrencyUseCase
import com.thesetox.exchange.usecase.GetListOfCurrencyRateUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exchangeModule =
    module {
        singleOf(::ExchangeDataRepository) { bind<ExchangeRepository>() }

        // use case
        singleOf(::GetListOfCurrencyRateUseCase)
        singleOf(::ConvertCurrencyUseCase)

        // viewmodel
        viewModel { ExchangeViewModel(get(), get()) }
    }
