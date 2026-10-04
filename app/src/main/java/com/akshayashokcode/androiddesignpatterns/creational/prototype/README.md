# Prototype (Creational) - Android importance: ⭐

## In one sentence
Create a new object by copying an existing one, then tweak the copy.

## Where / when to use it
- A template object that is costly to build from scratch (default report, default settings).
- You need a modified copy while keeping the original unchanged (undo, drafts).

## When NOT to use it
- Objects are cheap to build - just use the constructor.
- Beware shallow copies: nested mutable lists/objects must be copied too.

## Android examples
- Kotlin `data class.copy()`, `Intent(Intent)` copy constructor, `Bundle.clone()`, `Drawable.constantState.newDrawable()`.

## Example in this repo
`Report.kt`: `Report.copyOf()` duplicates the title and a new list of sections, so editing the copy never changes the original (checked by `prototype_copyIsIndependent`).

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
