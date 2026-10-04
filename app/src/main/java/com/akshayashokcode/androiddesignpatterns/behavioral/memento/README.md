# Memento (Behavioral) - Android importance: ⭐

## In one sentence
Capture an object state in a snapshot so it can be restored later.

## Where / when to use it
- Undo, drafts, checkpoints.
- Restoring state after rotation or process death.

## When NOT to use it
- State is huge - snapshots cost memory.

## Android examples
- `onSaveInstanceState(Bundle)`, `SavedStateHandle`, editor undo history, game save slots.

## Example in this repo
`TextHistory.kt`: `Editor.save()` returns a `Memento`; `History` stores them; `Editor.restore()` brings back the old text.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
