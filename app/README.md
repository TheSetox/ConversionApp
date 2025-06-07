# App Module

This module is the entry point of **ConversionApp**. It packages the Android application and wires together the core and feature modules.

## Key Classes

- **`ConversionApp`** – Application class that starts Koin and loads all modules.
- **`MainActivity`** – Launches the UI. It extends `SyncActivity` so that currency rates are automatically synced in the background. The activity hosts the Compose based `CurrencyExchangeScreen`.

## Dependencies

The Gradle file declares dependencies to:

- Core modules – `network`, `datastore`, `database`, and `designsystem`.
- Feature modules – `sync` and `exchange` for syncing rates and exchanging currencies.
- Koin for dependency injection and standard Android libraries.

Check `build.gradle.kts` for the full list of dependencies.

## Purpose

The app module ties everything together:

1. Initializes dependency injection via Koin.
2. Provides the launch activity with Compose UI.
3. Depends on other modules that implement the actual business logic.

