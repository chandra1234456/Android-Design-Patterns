# Flyweight (Structural) - Android importance: ⭐

## In one sentence
Share identical heavy data between many objects to save memory.

## Where / when to use it
- Thousands of similar objects (list rows with the same icon, map markers, text glyphs).
- The shared part can be immutable.

## When NOT to use it
- Few objects, or each object needs its own unique heavy data.

## Android examples
- RecyclerView view recycling, `LruCache` for bitmaps, `Integer.valueOf` cache, shared `Typeface`/`Paint`.

## Example in this repo
`IconFlyweight.kt`: `IconFactory.get(name)` returns the same `Icon` every time, so 1,000 rows share one object.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
