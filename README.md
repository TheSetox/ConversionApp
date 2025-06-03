# ConversionApp

<img src="https://github.com/user-attachments/assets/9ba540fa-e8c5-4e92-b40b-fb163e3af6f8" width="250"/>
<img src="https://github.com/user-attachments/assets/30b20cfe-acaf-4f8e-9c13-e164cf07bbc8" width="250"/>
<img src="https://github.com/user-attachments/assets/ed5a352c-ef8a-4e04-92fe-1a433ebdf095" width="250"/>


## 🚀 Introduction

**ConversionApp** is a currency exchange application that allows users to convert between currencies using up-to-date exchange rates. It syncs rates from a remote API, applies commissions based on specific rules, and keeps track of user balances — all in real-time.

---

## 🧰 Tech Stack & Libraries

* **Jetpack Compose** – Modern UI toolkit for native Android
* **Koin** – Lightweight dependency injection framework
* **Ktor Client** – HTTP client used for fetching exchange rates from a remote API
* **Room** – Local database for persisting currency rates
* **DataStore** – Store user preferences and app state persistently

---

## 🧱 Architecture

![image](https://github.com/user-attachments/assets/a7aba31e-041a-463f-aa64-9ff8fbe70c33)

ConversionApp follows a scalable and testable architecture:

* **MVVM (Model-View-ViewModel)** – For clear separation of concerns between UI and business logic
* **Clean Architecture** – Divides code into layers (UI, Domain, Data) to enforce boundaries and decoupling
* **Layer Per Feature** – Each feature (e.g., Exchange) has its own independent layers
* **Modular Approach** – Features and core layers are separated into modules for better reusability, testing, and build performance

  
### 🔍 Module Overview

* `app` is the main entry point and wires together the dependencies.
* `feature:exchange` handles the core exchange logic and business rules.
* `feature:sync` fetches the latest rates from the remote API and updates the database.
* `feature:commission` applies dynamic commission logic (e.g. first 5 free, 0.7% after).
* `feature:balance` manages the user's wallet-like balance.
* `core:network`, `core:datastore`, `core:database`, and `core:designsystem` are reusable base modules shared across features.

Each feature module depends only on what it needs and interacts with shared core modules through interfaces.

---


