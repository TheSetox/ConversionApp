package com.thesetox.balance

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin dependency module exposing balance related use cases.
 *
 * Registers [GetDefaultBalanceUseCase] so other components can
 * easily retrieve the initial wallet contents when the app starts.
 */
val balanceModule =
    module {
        singleOf(::GetDefaultBalanceUseCase)
    }
