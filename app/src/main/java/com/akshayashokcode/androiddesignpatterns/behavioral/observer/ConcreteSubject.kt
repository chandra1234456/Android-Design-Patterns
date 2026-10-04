package com.akshayashokcode.androiddesignpatterns.behavioral.observer

import java.util.concurrent.CopyOnWriteArrayList

/**
 * Subject holding a message and broadcasting changes to observers.
 *
 * Improvements over the naive version:
 * - [CopyOnWriteArrayList] makes iteration safe even if an observer unregisters itself (or another
 *   thread registers) during [notifyObservers] - a plain list throws ConcurrentModificationException.
 * - Registration is idempotent (no duplicate notifications).
 * - [message] is `@Volatile` so notifications from another thread see the latest value.
 */
class ConcreteSubject : Subject {
    private val observers = CopyOnWriteArrayList<Observer>()

    @Volatile
    private var message: String = ""

    override fun registerObserver(observer: Observer) {
        observers.addIfAbsent(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        val current = message
        observers.forEach { it.update(current) }
    }

    fun setMessage(message: String) {
        this.message = message
        notifyObservers()
    }
}
