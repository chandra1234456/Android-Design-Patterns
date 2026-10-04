# Mediator (Behavioral) - Android importance: ⭐

## In one sentence
A central object coordinates communication so components do not know each other.

## Where / when to use it
- Many components affect each other (form fields enabling a button).
- Direct references between components became a tangle.

## When NOT to use it
- Only two components - connect them directly.
- The mediator can become a god object.

## Android examples
- A ViewModel coordinating UI state, event bus, chat rooms, `MediaSession` between player and UI.

## Example in this repo
`ChatRoom.kt`: `User` only knows the `ChatMediator`; the room delivers messages to everyone else.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
