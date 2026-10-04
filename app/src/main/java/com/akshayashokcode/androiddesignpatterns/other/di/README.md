# Dependency Injection (Architectural) - Android importance: ⭐⭐⭐

## In one sentence
A class receives what it needs from outside instead of creating it.

## Where / when to use it
- Classes build their own dependencies and are hard to test.
- You want to swap implementations (real vs fake, prod vs debug).

## When NOT to use it
- Tiny apps where a plain constructor call is clear enough.

## Android examples
- Hilt/Dagger, Koin, constructor injection of repositories into ViewModels, `ViewModelProvider.Factory`.

## Example in this repo
`AppContainer.kt`: `OrderService` takes a `Logger` in its constructor; `AppContainer` creates one shared logger and wires it (what Hilt does automatically).

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
