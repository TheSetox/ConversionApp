# Datastore Module

This module wraps **Jetpack DataStore** so other modules can persist small pieces of data without needing to know the underlying implementation. It stores the hash of the downloaded currency rates and also exposes helpers for persisting the user's dark theme preference.

## Overview

- **AppDataStore** – interface that exposes `getCurrencyRateHash()`, `saveCurrencyRateHash()`, `isDarkThemeEnabled()` and `setDarkThemeEnabled()`.
- **LocalDataSource** – `AppDataStore` implementation backed by `androidx.datastore.preferences`.
- **dataStoreModule** – Koin module that provides a `DataStore<Preferences>` instance named `secure_prefs` and binds `AppDataStore` to `LocalDataSource`.

## Usage

Include `core:datastore` in your Gradle dependencies and load `dataStoreModule` when starting Koin:

```kotlin
startKoin {
    androidContext(this@App)
    modules(dataStoreModule)
}
```

Other modules can then inject `AppDataStore` to persist the currency rate hash or the user's dark theme setting.
