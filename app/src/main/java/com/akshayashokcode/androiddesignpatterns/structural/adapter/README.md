# Adapter Pattern (Structural)

## What it is
Converts the interface of an existing class into the one clients expect.

## When to use it
- Integrating a third-party/legacy API you can't change.
- Reusing a class whose interface doesn't match.

## How it is implemented here
Files: `PaymentAdapter.kt`. App code uses `PaymentProcessor` (rupees, Boolean). `LegacyGateway` (paise, HTTP-style codes) is the adaptee. `LegacyGatewayAdapter` converts units and result codes, isolating the app from the SDK.

## Android usage
`RecyclerView.Adapter` (data -> views), `ArrayAdapter`, wrapping SDK callbacks into `Flow`/`LiveData`.

## Pros / Cons
+ Isolates third-party code, swap SDKs in one place. - Extra layer.

## Interview highlights
- **Q: Class vs object adapter?** Kotlin uses object adapters (composition), as here.
- **Q: Android's best-known adapter?** `RecyclerView.Adapter`.
- **Highlight:** unit/rounding conversion lives in exactly one place.
