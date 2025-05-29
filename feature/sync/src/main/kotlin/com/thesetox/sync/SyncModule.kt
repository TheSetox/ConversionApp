package com.thesetox.sync

import com.thesetox.domain.SyncRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val syncModule =
    module {
        singleOf(::SyncDataRepository) { bind<SyncRepository>() }

        single<SyncUseCase> { SyncUseCase(get()) }
    }
