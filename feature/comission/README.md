# Commission Module

This module calculates the commission fee for currency conversions. It contains a flexible configuration that lets the app define how and when commissions are applied.

## Overview

The main entry point is `GetCommissionUseCase`, which keeps track of the number of conversions and determines the fee based on configurable rules:

- **Free conversions at the start**: The first few conversions can be exempt from any commission.
- **Conditional free conversions**: Every _n_-th conversion or specific amounts/currencies can be free.
- **Commission rate**: After the free quota, a percentage rate can be applied to the sell amount.

These rules are expressed using `CommissionConfig`, a small DSL-style builder that produces the commission for each transaction.

Koin is used to expose `GetCommissionUseCase` via `commissionModule` so it can be injected wherever conversion logic is performed.

## Default behavior

By default the app:

1. Allows the first five conversions for free.
2. Applies a 0.7% fee on subsequent conversions.

The configuration can easily be adjusted if different commission rules are required.
