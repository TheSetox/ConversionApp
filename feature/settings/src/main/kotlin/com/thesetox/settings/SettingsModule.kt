package com.thesetox.settings

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module that exposes the settings repository and use cases.
 */
val settingsModule =
    module {
        singleOf(::SettingsDataRepository) { bind<SettingsRepository>() }
        singleOf(::GetDarkThemeEnabledUseCase)
        singleOf(::SetDarkThemeEnabledUseCase)
    }
