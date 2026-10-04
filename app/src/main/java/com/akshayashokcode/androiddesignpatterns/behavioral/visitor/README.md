# Visitor (Behavioral) - Android importance: ⭐

## In one sentence
Add new operations to a set of classes without changing those classes.

## Where / when to use it
- A fixed set of item types (Book, Gadget) and many operations (tax, discount, export).
- Operation logic should live in one place, not in every item.

## When NOT to use it
- Item types change often (every visitor must be updated).
- In Kotlin, a `when` over a `sealed` type is usually simpler.

## Android examples
- Walking syntax trees, KSP/annotation processors, document exporters.

## Example in this repo
`CartVisitor.kt`: `TaxVisitor` computes tax differently for `Book` (0) and `Gadget` (18%).

See also: the [Design Patterns Guide](../../../../../../../../../docs/DESIGN_PATTERNS_GUIDE.md)
