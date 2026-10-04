# Factory / Factory Method Pattern (Creational)

## What it is
Encapsulates object creation behind an interface so callers don't depend on concrete classes.

## When to use it
- Concrete type is decided at runtime (config, user input, server flag).
- You want to add new variants without changing callers (Open/Closed).

## How it is implemented here
Files: `Notification.kt`. `NotificationFactory.create(type)` is a Simple Factory using an exhaustive `when` on an enum (compiler forces handling new types). `Notifier` is the **Factory Method**: abstract `createNotification()` is overridden by subclasses (`EmailNotifier`) while the template `notifyUser` stays fixed.

## Android usage
`Fragment` factories (`newInstance(args)`), `ViewModelProvider.Factory`, `LayoutInflater`, `Intent` creation helpers.

## Pros / Cons
+ Decoupling, easy to extend/test. - Extra classes; a plain `when` may be enough.

## Interview highlights
- **Q: Simple Factory vs Factory Method vs Abstract Factory?** Simple = one function with a switch (not a GoF pattern); Factory Method = subclass decides the product; Abstract Factory = factory of *families* of related products.
- **Q: Android example?** `ViewModelProvider.Factory` when a ViewModel needs constructor args.
- **Highlight:** exhaustive `when` = compile-time safety instead of a runtime default branch.
