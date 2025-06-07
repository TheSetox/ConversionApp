# Design System Module

The **designsystem** module centralizes theming and UI components for the app. It exposes common styles built with [Jetpack Compose](https://developer.android.com/jetpack/compose) so features can share a consistent look and feel.

## Features

- **Material 3 theme** – `ConversionAppTheme` provides light/dark color schemes and typography.
- **Color palette** – Predefined colors like `Purple80`, `Teal`, and others.
- **Typography & text styles** – Reusable styles such as `MediumTextStyle` and `ButtonTextStyle`.
- **UI components** – Lightweight Compose components:
  - `ConversionTopBar` – Center-aligned top bar with customizable title.
  - `ConversionTextField` – Basic single-line text field styled for numeric input.
  - `ConversionSpacer` – Sized spacer wrapper.

## Usage

Add the module as a dependency in another module:
```kotlin
implementation(project(":core:designsystem"))
```
Wrap screens with `ConversionAppTheme` and import the components as needed:
```kotlin
@Composable
fun ExampleScreen() {
    ConversionAppTheme {
        ConversionTopBar(stringId = R.string.sample_title)
        ConversionTextField(value = "100") { /* ... */ }
    }
}
```

The module contains its own `strings.xml` with shared string resources used by these components.

