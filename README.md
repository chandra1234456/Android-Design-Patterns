# Android Design Patterns

[![CI](https://github.com/chandra1234456/Android-Design-Patterns/actions/workflows/ci.yml/badge.svg)](https://github.com/chandra1234456/Android-Design-Patterns/actions/workflows/ci.yml)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-minSdk%2024-3DDC84?logo=android&logoColor=white)

24 design patterns implemented in Kotlin for Android, each with a plain-language explanation, real Android examples, unit tests, and a runnable demo app that shows every pattern's output on screen.

**Start here:** [Design Patterns Guide](docs/DESIGN_PATTERNS_GUIDE.md) - what each pattern is, when to use it, and where Android itself uses it.

## Highlights
- Creational, Structural, Behavioral and Architectural patterns (Singleton to Dependency Injection)
- Improved implementations: lazy Bill Pugh singleton, thread-safe Observer, immutable validated Builder
- Unit tests for every pattern (`PatternsTest`, `MorePatternsTest`)
- Demo app: tap a pattern to run the real code and see its output

## How to run
1. Clone the repo and open it in Android Studio (Hedgehog or newer, JDK 17).
2. Let Gradle sync, then press **Run** on an emulator or device (API 24+).
3. Pick a pattern from the list to see what it does.

Run the tests from a terminal:
```bash
./gradlew testDebugUnitTest
```

## Credits
Based on [AkshayAshokCode/Android-Design-Patterns](https://github.com/AkshayAshokCode/Android-Design-Patterns) (Singleton and Observer originals). This version improves those two, adds the remaining patterns, tests, the guide and the demo app.

## About
Maintained by [Balachandra Dasari](https://github.com/chandra1234456), Android developer.

## Contents

1. **Creational Patterns**
    - Singleton ✅
    - Builder ✅
    - Prototype ✅
    - Factory ✅
    - Factory Method ✅
    - Abstract Factory ✅

2. **Structural Patterns**
    - Adapter ✅
    - Decorator ✅
    - Proxy ✅
    - Composite ✅
    - Flyweight ✅
    - Facade ✅
    - Bridge ✅

3. **Behavioral Patterns**
    - Observer ✅
    - Strategy ✅
    - Command ✅
    - State ✅
    - Chain of Responsibility ✅
    - Mediator ✅
    - Memento ✅
    - Iterator ✅
    - Visitor ✅
    - Template Method ✅

4. **Other Patterns**
    - Dependency Injection ✅
    - Repository Pattern ✅

Each pattern's directory contains:
- A detailed `README.md` file explaining:
  - What the pattern is.
  - When to use it.
  - Why to use it.
  - How to use it (with an explanation of the example code).
  - Advantages.
  - Disadvantages.
  - Best Practices.
- Kotlin implementation files demonstrating the pattern.
- Example usage in an Android context.

## Purpose

The goal of this repository is to provide a reference for Android developers to understand and implement various design patterns. By studying these patterns, developers can improve their code quality, make their applications more robust, and follow best practices in software design.

Feel free to explore the patterns, contribute improvements, and share your own implementations!

## Contribution

Contributions are welcome! If you have improvements, bug fixes, or new patterns to add, please create a pull request. Ensure your code follows the Kotlin coding standards and includes proper documentation.

✅ = implemented. All listed patterns are done. Start with [docs/DESIGN_PATTERNS_GUIDE.md](docs/DESIGN_PATTERNS_GUIDE.md): plain-language explanations, when to use each, Android examples and a quick chooser.

## Pattern map (quick reference)

| Pattern | Category | Location | Real Android example | Core interview point |
|---|---|---|---|---|
| Singleton | Creational | `creational/singleton` | `Application`, Hilt `@Singleton` | Thread-safety, `object`, avoid leaking Context |
| Builder | Creational | `creational/builder` | `AlertDialog.Builder`, Retrofit | Immutability + validation |
| Factory / Factory Method | Creational | `creational/factory` | `ViewModelProvider.Factory` | Decouple creation from use |
| Observer | Behavioral | `behavioral/observer` | `LiveData`, `Flow` | Leaks, lifecycle, thread safety |
| Strategy | Behavioral | `behavioral/strategy` | `LayoutManager`, `Comparator` | Swap algorithm at runtime; vs State |
| Decorator | Structural | `structural/decorator` | `ContextWrapper`, `java.io` | Same interface, adds behaviour; `by` delegation |
| Adapter | Structural | `structural/adapter` | `RecyclerView.Adapter` | Convert incompatible interfaces |
| Repository | Architectural | `other/repository` | UI -> ViewModel -> Repository -> Room/Retrofit | Single source of truth |

## Interview cheat sheet
- **Creational** = how objects are made. **Structural** = how objects are composed. **Behavioral** = how objects communicate.
- Pairs interviewers love: Strategy vs State, Decorator vs Proxy vs Adapter, Factory Method vs Abstract Factory, Observer vs Pub/Sub.
- Tie patterns to SOLID: Strategy/Factory/Decorator -> Open/Closed; Repository/DI -> Dependency Inversion.
- Each folder README ends with an **Interview highlights** section.

## Tests
`./gradlew testDebugUnitTest` runs `PatternsTest` and `MorePatternsTest`, which exercise every pattern.
