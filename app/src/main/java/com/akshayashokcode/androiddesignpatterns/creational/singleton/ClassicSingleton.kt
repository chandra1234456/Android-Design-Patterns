package com.akshayashokcode.androiddesignpatterns.creational.singleton

/**
 * Classic lazy Singleton with a synchronized accessor.
 * Simple and correct, but every call takes the lock - kept as the baseline to compare against
 * [ThreadSafeSingleton] (double-checked locking), [BillPughSingleton] and [KotlinSingleton].
 */
class ClassicSingleton private constructor() {
    companion object {
        private var instance: ClassicSingleton? = null

        @Synchronized
        fun getInstance(): ClassicSingleton =
            instance ?: ClassicSingleton().also { instance = it }
    }
}
