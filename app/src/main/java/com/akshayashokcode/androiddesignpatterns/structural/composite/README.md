# Composite (Structural) - Android importance: ⭐⭐

## In one sentence
Treat a single object and a group of objects through the same interface, forming a tree.

## Where / when to use it
- Hierarchical data: menus, folders, comment threads, view trees.
- You want to run one operation (render, count, price) over the whole tree.

## When NOT to use it
- The structure is flat - a list is enough.

## Android examples
- `View` and `ViewGroup`, Jetpack Compose UI tree, nested navigation graphs, file explorers.

## Example in this repo
`UiComponent.kt`: `Label` is a leaf, `Container` holds other components; `count()` and `render()` work the same on a leaf or a whole tree.

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
