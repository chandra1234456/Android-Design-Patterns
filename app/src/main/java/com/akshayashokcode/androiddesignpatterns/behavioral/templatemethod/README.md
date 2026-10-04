# Template Method (Behavioral) - Android importance: ⭐⭐

## In one sentence
A base class fixes the order of steps; subclasses fill in the details.

## Where / when to use it
- Several classes share the same workflow but differ in some steps (fetch, save, notify).
- You want to prevent subclasses from changing the overall order.

## When NOT to use it
- Many steps vary - prefer composition (Strategy).

## Android examples
- `Activity`/`Fragment` lifecycle callbacks, `RecyclerView.Adapter`, `CoroutineWorker.doWork()`, base sync classes.

## Example in this repo
`SyncTask.kt`: `run()` is fixed (start, fetch, save, finish); `UserSyncTask` supplies `fetch()` and `save()`.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
