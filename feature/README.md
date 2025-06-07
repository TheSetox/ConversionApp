# Feature Modules

This folder contains the feature-specific modules that make up ConversionApp. Each module is an independent Android library that exposes its functionality via Koin.

## balance

Provides the wallet handling logic. It exposes `GetDefaultBalanceUseCase`, which returns an initial balance of 1000 EUR and defines the `Balance` model used across the app.

## comission

Implements the commission fee calculation for currency exchanges. Commission rules are defined in `CommissionConfig` and executed through `GetCommissionUseCase`.

## exchange

Handles the currency exchange flow, including Compose UI components and several use cases such as `ConvertCurrencyUseCase` and `ExchangeCurrencyUseCase`. It reads rates from the database and applies commission and balance updates.

## sync

Responsible for synchronizing currency rates from the remote API. `SyncUseCase` checks if an update is needed, saves rates to the database and stores an MD5 hash to avoid unnecessary work. A background `SyncService` triggers the process.
