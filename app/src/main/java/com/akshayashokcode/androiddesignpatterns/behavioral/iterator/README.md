# Iterator (Behavioral) - Android importance: ⭐

## In one sentence
Walk through a collection one element (or page) at a time without exposing its internals.

## Where / when to use it
- Traverse data without knowing how it is stored.
- Paginate a big list.

## When NOT to use it
- A normal Kotlin collection already gives you `for`, `map`, `filter`.

## Android examples
- Kotlin `Iterator`/`Sequence`, database `Cursor`, Paging 3 loading page by page.

## Example in this repo
`PageIterator.kt`: implements `Iterator<List<T>>` returning pages of a given size.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
