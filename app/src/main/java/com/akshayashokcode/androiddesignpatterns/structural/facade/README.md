# Facade (Structural) - Android importance: ⭐⭐

## In one sentence
One simple class that hides a complicated group of classes behind an easy method.

## Where / when to use it
- A task needs several classes in a fixed order (compress, authenticate, upload).
- You want to hide a third-party SDK from the rest of the app.

## When NOT to use it
- It only forwards calls without simplifying anything.
- It grows into a god class that does everything.

## Android examples
- Upload/Payment managers, one `AnalyticsTracker` wrapping several SDKs, `WorkManager` hiding schedulers.

## Example in this repo
`UploadFacade.kt`: `upload(file)` internally compresses, gets a token and calls the API.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
