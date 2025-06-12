package com.thesetox.sync

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module that wires up the dependencies used by the sync feature.
 *
 * This module provides [SyncDataRepository] as the implementation for [SyncRepository]
 * and exposes [SyncUseCase] so other modules can trigger synchronization.
 */
val syncModule =
    module {
        singleOf(::SyncDataRepository) { bind<SyncRepository>() }

        singleOf(::SyncUseCase)
    }
