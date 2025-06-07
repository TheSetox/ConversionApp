# Database Module

This module provides the local storage layer using [Room](https://developer.android.com/training/data-storage/room).
It stores currency rates that are synchronized from the network and exposes them through a DAO.

## Features

- **Room database** named `conversion.db` with a single table `currency_rate`.
- **Data access** via `CurrencyRateDao` which allows observing rates, fetching a single rate, inserting new rates and clearing the table.
- **Dependency injection** with Koin via `DatabaseModule` for easy database and DAO provisioning.

## Main Classes

- `CurrencyRateEntity` – Room entity representing a currency rate entry.
- `CurrencyRateDao` – DAO with queries for retrieving and updating rates.
- `ConversionAppDatabase` – `RoomDatabase` implementation registering the DAO.
- `DatabaseModule` – Koin module that builds the database and exposes the DAO.

This module is used by the feature modules to cache exchange rates locally so they can be displayed even when offline.

