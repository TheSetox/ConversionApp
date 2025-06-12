package com.thesetox.settings

/**
 * Repository interface for storing and retrieving user settings.
 */
interface SettingsRepository {
    /**
     * Returns whether dark theme is enabled.
     */
    suspend fun isDarkThemeEnabled(): Boolean

    /**
     * Persists the dark theme preference.
     */
    suspend fun setDarkThemeEnabled(enabled: Boolean)
}
