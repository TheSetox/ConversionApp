# Network Module

The **core:network** module centralizes all networking functionality in ConversionApp. It defines the HTTP client and API layer used by other modules to talk with the remote service.

## Highlights

- **Ktor client** – Configured with `ContentNegotiation` and Kotlinx serialization.
- **ApiResult** – Sealed class wrapping successful responses or errors.
- **CurrencyRateApi** – Interface for fetching exchange rates with `CurrencyRateRemoteSource` as the implementation.
- **Koin integration** – `networkModule` provides the client, JSON serializer and API bindings.

## Usage

Add the module to your Koin setup:

```kotlin
startKoin {
    modules(networkModule)
}
```

Then inject `CurrencyRateApi` wherever it is needed:

```kotlin
class ExchangeRepository(private val api: CurrencyRateApi) {
    suspend fun loadRates() = api.fetchCurrencyRates()
}
```

`CurrencyRateRemoteSource` currently requests the following endpoint:

```
https://developers.paysera.com/tasks/api/currency-exchange-rates
```

Additional API calls can be added by extending `CurrencyRateApi`.
