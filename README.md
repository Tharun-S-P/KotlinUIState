# Kotlin UI State Management — Desktop Example

A beginner-friendly Kotlin + Jetpack Compose Desktop project demonstrating four UI states:

- **Loading** — shows a progress indicator.
- **Success** — displays employee records.
- **Empty** — shows that no employees are available.
- **Error** — displays an error message.

The buttons simulate state changes locally; this sample does not call a backend API.

## Run in IntelliJ IDEA

1. Extract the ZIP file.
2. Open the extracted `KotlinUIStateManagement` folder in IntelliJ IDEA.
3. Use JDK 17 or a compatible newer JDK.
4. Allow Gradle to sync and download dependencies.
5. Open the Gradle tool window and run `app > Tasks > compose desktop > run`.

Alternatively, in the project root terminal, run:

```bash
gradle :app:run
```

If Gradle is not installed, use IntelliJ IDEA's Gradle tool window or generate a Gradle wrapper from IntelliJ.

## How it works

`EmployeeUiState` is a sealed interface. `uiState` is Compose state created with `remember { mutableStateOf(...) }`. When a button updates `uiState`, Compose recomposes the UI and the `when` expression displays the matching state.

## Files

- `app/src/main/kotlin/Main.kt` — complete runnable application.
- `app/build.gradle.kts` — Compose Desktop dependencies and run configuration.
