# Settings Module

This module stores simple user preferences for **ConversionApp**. It currently keeps a single flag indicating whether dark theme is enabled.

## Components

- `SettingsRepository` – interface for reading and updating preferences.
- `SettingsDataRepository` – DataStore based implementation.
- `GetDarkThemeEnabledUseCase` – returns the saved dark theme value.
- `SetDarkThemeEnabledUseCase` – updates the preference.
- `settingsModule` – Koin module exposing the repository and use cases.

Include `settingsModule` in your Koin setup and depend on `:feature:settings` to read or change the dark theme setting.
