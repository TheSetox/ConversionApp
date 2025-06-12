package com.thesetox.comission

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module that provides dependencies for the commission feature.
 *
 * Currently it exposes a singleton of [GetCommissionUseCase]. Include this
 * module in your Koin setup to enable commission calculation.
 */
val commissionModule =
    module {
        singleOf(::GetCommissionUseCase)
    }
