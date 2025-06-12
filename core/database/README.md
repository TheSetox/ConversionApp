# Database Module

This module provides the local storage layer using [Room](https://developer.android.com/training/data-storage/room).
It stores currency rates that are synchronized from the network and exposes them through a DAO.

## Features

- **Room database** named `conversion.db` with tables `currency_rate` and `balance`.
- **Data access** via `CurrencyRateDao` and `BalanceDao` for observing, updating and clearing data.
- **Dependency injection** with Koin via `DatabaseModule` for easy database and DAO provisioning.

## Main Classes

- `CurrencyRateEntity` – Room entity representing a currency rate entry.
- `CurrencyRateDao` – DAO with queries for retrieving and updating rates.
- `BalanceDao` – DAO for reading and updating wallet balances.
- `ConversionAppDatabase` – `RoomDatabase` implementation registering the DAOs.
- `DatabaseModule` – Koin module that builds the database and exposes the DAOs.

This module is used by the feature modules to cache exchange rates locally so they can be displayed even when offline.

