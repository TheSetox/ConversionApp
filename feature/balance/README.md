# Balance Module

This module provides the basic balance functionality for **ConversionApp**.
It is responsible for exposing the user's starting wallet and related utilities
so other features (like exchanging currencies) can easily access it.

## Contents

- `Balance.kt` – simple data class representing a balance entry with a currency
  `code` and numeric `value`.
- `GetDefaultBalanceUseCase.kt` – returns a list containing the default balance
  used when the app is first launched. Currently this is `1000 EUR`.
- `BalanceModule.kt` – Koin module that registers the use case for dependency
  injection.

## Usage

Include `balanceModule` in your Koin setup and inject
`GetDefaultBalanceUseCase` wherever the initial wallet is needed.

The module only depends on **Koin**, making it lightweight and easily
reusable across other modules.
