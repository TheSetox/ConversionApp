package com.thesetox.settings

import com.thesetox.datastore.AppDataStore

/**
 * DataStore-based implementation of [SettingsRepository].
 */
class SettingsDataRepository(
    private val dataStore: AppDataStore,
) : SettingsRepository {
    override suspend fun isDarkThemeEnabled(): Boolean {
        return dataStore.isDarkThemeEnabled()
    }

    override suspend fun setDarkThemeEnabled(enabled: Boolean) {
        dataStore.setDarkThemeEnabled(enabled)
    }
}
