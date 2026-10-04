package com.akshayashokcode.androiddesignpatterns.creational.singleton

/**
 * Shared, mutable app configuration exposed as a Singleton.
 *
 * Uses double-checked locking via [ThreadSafeSingleton]'s idiom: only the first call pays for
 * synchronization (the old version locked on every `getInstance()` and used `instance!!`).
 * [configValue] is `@Volatile` so writes on one thread are visible to readers on others.
 */
class AppConfig private constructor() {
    @Volatile
    var configValue: String? = null

    companion object {
        @Volatile
        private var instance: AppConfig? = null

        fun getInstance(): AppConfig =
            instance ?: synchronized(this) {
                instance ?: AppConfig().also { instance = it }
            }
    }
}
