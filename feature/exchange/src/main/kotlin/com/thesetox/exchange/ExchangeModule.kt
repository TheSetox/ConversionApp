package com.thesetox.exchange

import com.thesetox.exchange.repository.ExchangeDataRepository
import com.thesetox.exchange.repository.ExchangeRepository
import com.thesetox.exchange.ui.ExchangeViewModel
import com.thesetox.exchange.usecase.ConvertCurrencyUseCase
import com.thesetox.exchange.usecase.ExchangeCurrencyUseCase
import com.thesetox.exchange.usecase.GetCommissionUseCase
import com.thesetox.exchange.usecase.GetListOfCurrencyUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exchangeModule =
    module {
        singleOf(::ExchangeDataRepository) { bind<ExchangeRepository>() }

        // use case
        singleOf(::GetListOfCurrencyUseCase)
        singleOf(::ConvertCurrencyUseCase)
        singleOf(::ExchangeCurrencyUseCase)
        singleOf(::GetCommissionUseCase)

        // viewmodel
        viewModel { ExchangeViewModel(get(), get(), get(), get()) }
    }
