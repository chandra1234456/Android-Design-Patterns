package com.akshayashokcode.androiddesignpatterns.behavioral.memento

/** Memento: an immutable snapshot of the editor's state. */
class Memento(val content: String)

/** Originator: creates snapshots of itself and can restore from them. */
class Editor {
    var content = ""
    fun save() = Memento(content)
    fun restore(m: Memento) { content = m.content }
}

/** Caretaker: stores snapshots but never looks inside them. */
class History {
    private val snapshots = ArrayDeque<Memento>()
    fun push(m: Memento) = snapshots.addLast(m)
    fun pop(): Memento? = snapshots.removeLastOrNull()
}
