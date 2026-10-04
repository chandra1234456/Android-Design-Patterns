package com.akshayashokcode.androiddesignpatterns.creational.prototype

/** Anything that can produce an independent copy of itself. */
interface Prototype<T> {
    fun copyOf(): T
}

/** A report template: clone it, then change only what differs. The original stays untouched. */
data class Report(val title: String, val sections: MutableList<String>) : Prototype<Report> {
    // Deep copy: the list is duplicated too, otherwise both reports would share it.
    override fun copyOf() = Report(title, sections.toMutableList())
}
