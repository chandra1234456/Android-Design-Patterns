# State (Behavioral) - Android importance: ⭐⭐

## In one sentence
An object changes its behaviour when its internal state changes.

## Where / when to use it
- Behaviour depends on status: player (stopped/playing/paused), order, login, download.
- You see many `if (status == ...)` checks spread around.

## When NOT to use it
- Only two simple states - a Boolean is fine.

## Android examples
- Media players, `sealed class UiState` (Loading, Success, Error) in MVVM, download managers, Bluetooth connection states.

## Example in this repo
`PlayerState.kt`: each state object decides what `onPlayPause()` returns, so `Player.tap()` has no conditions.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
