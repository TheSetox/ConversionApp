package com.thesetox.settings

/**
 * Updates the stored dark theme preference.
 */
class SetDarkThemeEnabledUseCase(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.setDarkThemeEnabled(enabled)
    }
}
