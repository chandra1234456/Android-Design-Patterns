# Observer Pattern

## What it is
The Observer Pattern is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

## When to use it
- When changes to one object need to be communicated to multiple dependent objects.
- When an object needs to notify other objects without knowing who or how many objects need to be notified.

## Why to use it
- To achieve loose coupling between objects that interact.
- To ensure that changes in one part of the application are automatically communicated to other parts of the application.

## How to use it
- Define an `Observer` interface with an `update` method.
- Define a `Subject` interface with methods to register, remove, and notify observers.
- Implement the `Subject` interface in a concrete class that maintains a list of observers and notifies them when its state changes.
- Implement the `Observer` interface in concrete classes that define the `update` method to respond to notifications.

## Advantages
- Promotes loose coupling between the subject and its observers.
- Facilitates a broadcast communication model.

## Disadvantages
- Can lead to memory leaks if observers are not properly removed.
- Can be challenging to debug due to the indirect nature of the communication.

## Best Practices
- Ensure proper management of observer registration and removal to avoid memory leaks.
- Use weak references or other techniques to prevent observers from keeping strong references to the subject, which can help avoid memory leaks.

## Example

### Observer Interface

```kotlin
interface Observer {
    fun update(message: String)
}


## Implementation review & improvements
- `Observer` is now a `fun interface`, so a lambda can be registered.
- `ConcreteSubject` uses `CopyOnWriteArrayList`: an observer can unregister itself inside `update()` without a `ConcurrentModificationException`, and registration from other threads is safe.
- `registerObserver` is idempotent (no duplicate notifications); `message` is `@Volatile`.
- Covered by `PatternsTest.observer_selfRemovalDuringNotifyIsSafe`.

## Android usage
`LiveData`, `StateFlow`/`SharedFlow`, `BroadcastReceiver`, `View.OnClickListener`, `RxJava`. Lifecycle-aware observers (`LifecycleOwner`) solve the "forgot to unregister" leak.

## Interview highlights
- **Observer vs Pub/Sub?** Observer: subject knows its observers directly. Pub/Sub: a broker decouples publisher and subscriber (EventBus, Kafka).
- **LiveData vs StateFlow?** LiveData is lifecycle-aware and Android-only; StateFlow is Kotlin-native, always has a value, needs `repeatOnLifecycle` to be lifecycle-safe.
- **Common bug:** memory leaks from un-removed observers; notifying on the wrong thread.
