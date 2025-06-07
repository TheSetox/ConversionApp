# Core Modules

The **core** directory groups together libraries that supply shared functionality
for the rest of `ConversionApp`. Every module can be used independently and
exposes its own Koin definitions so features only depend on what they need.

## Modules

- **`designsystem`** – Compose widgets and theming utilities that define the
  project's look and feel.
- **`network`** – Ktor client setup along with the interface for retrieving
  currency exchange rates from a remote endpoint. Responses are wrapped in a
  simple `ApiResult` type.
- **`database`** – Room database, DAO and entity definitions used to persist
  exchange rates locally on the device.
- **`datastore`** – Lightweight wrapper around Jetpack DataStore for storing
  small pieces of app state, such as the hash of the latest fetched rates.

Keeping these modules loosely coupled encourages modularization and makes the
codebase easier to test.
