# Transaction Module

This module records a history of completed currency exchanges.

## Overview
- `Transaction.kt` – data model describing a single exchange.
- `TransactionRepository` – interface for storing and retrieving transactions.
- `TransactionDataRepository` – simple in-memory implementation.
- `AddTransactionUseCase` – saves a transaction to the repository.
- `GetTransactionsUseCase` – returns the stored list of transactions.
- `transactionModule` – Koin definition exposing the repository and use cases.

Use the provided use cases to persist exchanges and display the transaction list in other features.
