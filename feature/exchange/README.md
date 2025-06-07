# Exchange Module

This module implements the currency exchange feature of **ConversionApp**. It contains the UI, business logic and data access required to convert one currency to another and apply the corresponding commission fees.

## Main responsibilities

- Display available currencies and user balances
- Convert amounts between currencies based on latest rates stored in the database
- Execute exchanges while applying commission rules and updating balances
- Expose a `ExchangeViewModel` that coordinates the state and side effects

## Structure

```
feature/exchange
├── repository   # data access layer
├── usecase      # business rules
├── model        # UI state and results
└── ui           # Compose screens and components
```

### Dependency Injection

The `ExchangeModule.kt` file defines a Koin module providing all dependencies such as repositories, use cases and the `ExchangeViewModel`.

### Key classes

- **ExchangeViewModel** – Handles user events, performs conversions and exchanges.
- **ConvertCurrencyUseCase** – Converts an amount from one currency to another.
- **ExchangeCurrencyUseCase** – Applies commission fees and updates balances during an exchange.
- **ExchangeDataRepository** – Retrieves currency rates from the local database.

## Usage

Include `:feature:exchange` in your app module and load `exchangeModule` when initializing Koin. The UI can be displayed using `CurrencyExchangeScreen` from the `ui` package.
