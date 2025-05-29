package com.thesetox.sync

import com.thesetox.sync.usecase.SyncApiUseCase
import com.thesetox.sync.usecase.SyncUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val syncModule =
    module {
        singleOf(::SyncDataRepository) { bind<SyncRepository>() }

        single<SyncApiUseCase> { SyncApiUseCase(get()) }
        single<SyncUseCase> { SyncUseCase(get()) }
    }
