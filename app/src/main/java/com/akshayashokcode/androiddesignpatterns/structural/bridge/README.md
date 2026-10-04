# Bridge (Structural) - Android importance: ⭐

## In one sentence
Separate WHAT something is from HOW it is done so both can change independently.

## Where / when to use it
- Two dimensions of variation: alert type (urgent, normal) and channel (email, SMS).
- You would otherwise need a class for every combination.

## When NOT to use it
- Only one dimension varies - Strategy or inheritance is enough.

## Android examples
- Notification type x delivery channel, UI abstraction x platform renderer, logger x output target.

## Example in this repo
`Alert.kt`: `UrgentAlert`/`NormalAlert` hold a `MessageSender` (`EmailSender`, `SmsSender`), so adding `PushSender` needs one new class.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
