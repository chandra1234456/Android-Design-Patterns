package com.akshayashokcode.androiddesignpatterns.behavioral.iterator

/** Walks a list one page at a time without exposing how the list is stored. */
class PageIterator<T>(private val items: List<T>, private val pageSize: Int) : Iterator<List<T>> {
    init { require(pageSize > 0) { "pageSize must be positive" } }

    private var index = 0

    override fun hasNext() = index < items.size

    override fun next(): List<T> {
        if (!hasNext()) throw NoSuchElementException()
        val end = minOf(index + pageSize, items.size)
        return items.subList(index, end).also { index = end }
    }
}
