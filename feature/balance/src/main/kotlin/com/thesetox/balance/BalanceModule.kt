package com.thesetox.balance

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val balanceModule =
    module {
        singleOf(::GetDefaultBalanceUseCase)
    }
