# Command (Behavioral) - Android importance: ⭐⭐

## In one sentence
Wrap an action as an object so it can be stored, queued, logged or undone.

## Where / when to use it
- Undo/redo in an editor.
- Queue or schedule work, retry failed work.
- Decouple the button from what it does.

## When NOT to use it
- A simple one-off call; a lambda is enough.

## Android examples
- `Runnable` posted to a `Handler`, `WorkManager` requests, Room transactions, text-editor undo.

## Example in this repo
`Command.kt`: `AppendCommand` has `execute()` and `undo()`; `CommandHistory` keeps a stack so `undo()` reverses the last action.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
