package com.akshayashokcode.androiddesignpatterns.behavioral.observer

/** Publisher side of the Observer pattern. */
interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}
