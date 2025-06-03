package com.thesetox.comission

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commissionModule =
    module {
        singleOf(::GetCommissionUseCase)
    }
