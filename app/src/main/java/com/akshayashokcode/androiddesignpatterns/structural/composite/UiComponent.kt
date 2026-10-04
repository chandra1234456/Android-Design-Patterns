package com.akshayashokcode.androiddesignpatterns.structural.composite

/** Common interface so a single item and a group of items are treated the same way. */
interface UiComponent {
    fun render(indent: Int = 0): String
    fun count(): Int
}

class Label(private val text: String) : UiComponent {
    override fun render(indent: Int) = " ".repeat(indent) + "Label($text)"
    override fun count() = 1
}

/** Composite: holds children, which may be leaves or other containers (a tree, like ViewGroup). */
class Container(private val name: String) : UiComponent {
    private val children = mutableListOf<UiComponent>()

    fun add(child: UiComponent) = apply { children += child }

    override fun render(indent: Int) =
        (listOf(" ".repeat(indent) + "Container($name)") + children.map { it.render(indent + 2) })
            .joinToString("\n")

    override fun count() = 1 + children.sumOf { it.count() }
}
