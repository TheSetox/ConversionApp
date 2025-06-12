package com.thesetox.settings

/**
 * Retrieves the saved dark theme setting.
 */
class GetDarkThemeEnabledUseCase(
    private val repository: SettingsRepository,
) {
    suspend operator fun invoke(): Boolean = repository.isDarkThemeEnabled()
}
