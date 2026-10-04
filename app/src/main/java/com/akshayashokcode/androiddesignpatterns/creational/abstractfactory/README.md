# Abstract Factory (Creational) - Android importance: ⭐

## In one sentence
A factory of factories that produces a whole family of matching objects.

## Where / when to use it
- Parts must always be used together: light theme button + light theme dialog.
- Switching a whole family in one place (dark mode, dev vs prod services, per-platform UI).

## When NOT to use it
- Only one product type is needed - use a plain Factory.
- Adding a new product type forces changes in every factory.

## Android examples
- Theme/widget families, build-flavor service sets, Dagger/Hilt modules choosing prod vs fake implementations.

## Example in this repo
`UiFactory.kt`: `uiFactoryFor(darkMode)` returns a factory that creates a matching button and dialog; callers never mix light and dark parts.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
