/**
 * Koin module that wires all dependencies required by the exchange feature.
 *
 * This module provides the concrete implementation of [ExchangeRepository], the
 * business use cases for currency conversion and exchange, and finally the
 * [ExchangeViewModel] used by the UI layer.
 */
package com.thesetox.exchange

import com.thesetox.exchange.repository.ExchangeDataRepository
import com.thesetox.exchange.repository.ExchangeRepository
import com.thesetox.exchange.ui.ExchangeViewModel
import com.thesetox.exchange.usecase.ConvertCurrencyUseCase
import com.thesetox.exchange.usecase.ExchangeCurrencyUseCase
import com.thesetox.exchange.usecase.GetListOfCurrencyUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/** Koin module that provides repositories, use cases and the [ExchangeViewModel]. */
val exchangeModule =
    module {
        singleOf(::ExchangeDataRepository) { bind<ExchangeRepository>() }

        // use case
        singleOf(::GetListOfCurrencyUseCase)
        singleOf(::ConvertCurrencyUseCase)
        singleOf(::ExchangeCurrencyUseCase)

        // viewmodel
        viewModel { ExchangeViewModel(get(), get(), get(), get()) }
    }
