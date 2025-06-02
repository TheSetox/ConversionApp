package com.thesetox.exchange

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exchangeModule =
    module {
        singleOf(::ExchangeDataRepository) { bind<ExchangeRepository>() }

        // use case
        singleOf(::GetListOfCurrencyRateUseCase)

        // viewmodel
        viewModel { ExchangeViewModel(get()) }
    }
