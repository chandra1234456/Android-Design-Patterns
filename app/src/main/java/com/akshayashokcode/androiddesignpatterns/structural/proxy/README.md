# Proxy (Structural) - Android importance: ⭐⭐

## In one sentence
A stand-in object with the same interface that controls access to the real one.

## Where / when to use it
- Cache results of an expensive call (network image).
- Lazy-create a heavy object only when first used.
- Add access control, logging or retry around an object.

## When NOT to use it
- The real object is cheap and always needed.
- Do not hide very slow work behind what looks like a cheap call without documenting it.

## Android examples
- Image loading caches, Retrofit creating your API interface with a dynamic proxy, `by lazy`, AIDL Binder proxies.

## Example in this repo
`ImageProxy.kt`: `CachingImageProxy` wraps `RealImageLoader` and downloads each URL only once.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
