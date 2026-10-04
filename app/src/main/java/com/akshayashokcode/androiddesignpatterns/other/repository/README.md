# Repository Pattern (Architectural)

## What it is
Mediates between data sources (network, DB, cache) and the rest of the app, exposing a clean data API.

## When to use it
- More than one data source.
- You want ViewModels/UseCases to be testable without Android/network.

## How it is implemented here
Files: `UserRepository.kt`. `UserRepository` is the abstraction; `DefaultUserRepository` is cache-first: read local, else fetch remote and save locally. Sources are interfaces, so tests inject fakes (see `PatternsTest`).

## Android usage
Recommended by Google's app architecture guide: UI -> ViewModel -> Repository -> (Room / Retrofit). Usually wired with Hilt/Koin (Dependency Injection).

## Pros / Cons
+ Single source of truth, testable, swappable sources. - Boilerplate for trivial apps.

## Interview highlights
- **Q: Where does caching logic live?** In the repository, not the ViewModel.
- **Q: Repository vs DAO?** DAO = one table/source API; Repository = coordinates several sources.
- **Highlight:** constructor injection of interfaces (Dependency Inversion).
