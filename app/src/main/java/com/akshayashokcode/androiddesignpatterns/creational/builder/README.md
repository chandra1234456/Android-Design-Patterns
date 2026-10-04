# Builder Pattern (Creational)

## What it is
Separates construction of a complex object from its representation so the same process can build different configurations.

## When to use it
- Many optional parameters (telescoping constructors).
- Objects that must be immutable but validated before creation.

## How it is implemented here
Files: `HttpRequest.kt`. `HttpRequest` has a private constructor; `HttpRequest.Builder` collects values via chained setters (`apply`), validates in `build()`, and returns an immutable object. `httpRequest(url) { ... }` is the idiomatic Kotlin DSL wrapper.

## Android usage
`AlertDialog.Builder`, `NotificationCompat.Builder`, `Retrofit.Builder`, `OkHttpClient.Builder`, `Room.databaseBuilder`.

## Pros / Cons
+ Readable call sites, immutability, central validation. - More boilerplate; in Kotlin, named/default arguments often suffice for simple cases.

## Interview highlights
- **Q: Builder vs named/default arguments?** Defaults cover simple cases; Builder wins when validation across fields, step-wise construction, or Java interop is needed.
- **Q: Builder vs Factory?** Factory chooses *which* type; Builder controls *how* one complex object is assembled.
- **Spot the highlight:** validation in `build()` (cross-field rule: body only on POST/PUT/PATCH) and defensive copy `headers.toMap()` keep the product truly immutable.
