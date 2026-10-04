# Decorator Pattern (Structural)

## What it is
Attaches extra behaviour to an object dynamically by wrapping it in objects with the same interface.

## When to use it
- Add responsibilities without subclass explosion.
- Combine features in different orders at runtime.

## How it is implemented here
Files: `DataSource.kt`. `InMemoryDataSource` is the component; `DataSourceDecorator` uses Kotlin **class delegation** (`by wrappee`) to remove forwarding boilerplate; `ReverseDecorator` and `UpperCaseOnWriteDecorator` override only what they change and can be stacked.

## Android usage
`java.io` streams (`BufferedInputStream`), `ContextWrapper`/`ContextThemeWrapper`, OkHttp interceptors (chain-style cousin).

## Pros / Cons
+ Flexible, single-responsibility wrappers. - Many small objects; order of wrapping matters; hard to debug deep stacks.

## Interview highlights
- **Q: Decorator vs Proxy vs Adapter?** Decorator: same interface, adds behaviour. Proxy: same interface, controls access. Adapter: *different* interface, converts it.
- **Q: Why not inheritance?** N features -> 2^N subclasses.
- **Highlight:** `by` delegation = idiomatic Kotlin decorator.
