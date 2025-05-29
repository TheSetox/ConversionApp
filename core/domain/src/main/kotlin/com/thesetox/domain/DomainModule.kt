package com.thesetox.domain

import org.koin.dsl.module

val domainModule =
    module {
        single<FetchCurrencyRatesUseCase> { FetchCurrencyRatesUseCase(get()) }
    }
