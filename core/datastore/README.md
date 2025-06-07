# Datastore Module

This module wraps **Jetpack DataStore** so other modules can persist small pieces of data without needing to know the underlying implementation. Currently it stores a hash of the downloaded currency rates which the sync feature uses to determine when new data is available.

## Overview

- **AppDataStore** – interface that exposes `getCurrencyRateHash()` and `saveCurrencyRateHash()`.
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

Other modules can then inject `AppDataStore` to read or write the saved hash.
