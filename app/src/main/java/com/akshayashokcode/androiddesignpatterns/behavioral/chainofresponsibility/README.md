# Chain of Responsibility (Behavioral) - Android importance: ⭐⭐

## In one sentence
Pass a request along a chain of handlers until one handles it (or all have checked it).

## Where / when to use it
- Validation pipelines (not empty, min length, no spaces).
- Request processing steps that can be added or removed (auth, logging, caching).

## When NOT to use it
- A single fixed check.

## Android examples
- OkHttp interceptors, touch-event dispatch through views, form validation, deep-link handlers.

## Example in this repo
`Validators.kt`: `NotEmptyValidator.then(MinLengthValidator).then(NoSpacesValidator)`; `handle()` returns the first error or null.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
