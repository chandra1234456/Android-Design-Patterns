# Strategy Pattern (Behavioral)

## What it is
Defines a family of interchangeable algorithms behind one interface, selectable at runtime.

## When to use it
- Many `if/else`/`when` branches choosing an algorithm (sorting, pricing, validation).
- Behaviour must change at runtime.

## How it is implemented here
Files: `DiscountStrategy.kt`. `DiscountStrategy` is a `fun interface` (lambdas work); `NoDiscount`, `PercentageDiscount`, `FlatDiscount` are strategies; `Checkout` is the context and its `strategy` can be swapped at runtime. Inputs are validated in the strategy's `init`.

## Android usage
`RecyclerView.LayoutManager`, `Comparator`, image-loading/caching policies, Retrofit `Converter.Factory`.

## Pros / Cons
+ Open/Closed, easy unit testing per strategy. - More types; clients must know strategies.

## Interview highlights
- **Q: Strategy vs State?** Strategy is chosen by the client and rarely changes itself; State transitions happen internally as the object's state changes.
- **Q: Strategy in Kotlin?** Often just a lambda / function type `(Double) -> Double`.
- **Highlight:** swapping behaviour with zero conditionals in `Checkout`.
