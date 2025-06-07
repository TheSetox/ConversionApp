# Sync Module

The **sync** feature is responsible for keeping currency rates in sync with the remote API.
It exposes a small set of classes that run in the background and update the local Room
database only when rates change.

## How it works
1. `SyncService` runs in the background and periodically executes `SyncUseCase`.
   It triggers a synchronization every **5 seconds** as defined in
   [`SyncService`](src/main/kotlin/com/thesetox/sync/SyncService.kt).
2. `SyncUseCase` downloads the latest rates and calculates an MD5 hash of the response.
3. The current hash is compared with the last stored one in `AppDataStore`.
4. If the hash differs, the rates are stored to the database via `CurrencyRateDao` and the new
   hash is persisted.

The service can be bound from an `Activity` (see `SyncActivity`) and uses Koin for dependency
injection.

## Provided Koin module
```
val syncModule = module {
    singleOf(::SyncDataRepository) { bind<SyncRepository>() }
    singleOf(::SyncUseCase)
}
```

## Gradle dependencies
The module relies on the following core modules and libraries:
- `core:network` – fetches the remote exchange rates.
- `core:datastore` – stores the last sync hash.
- `core:database` – persists the currency rates locally.
- Kotlin coroutines, Koin, and kotlinx.serialization.
