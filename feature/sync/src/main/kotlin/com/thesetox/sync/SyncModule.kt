package com.thesetox.sync

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val syncModule =
    module {
        singleOf(::SyncDataRepository) { bind<SyncRepository>() }

        singleOf(::SyncUseCase)
    }
