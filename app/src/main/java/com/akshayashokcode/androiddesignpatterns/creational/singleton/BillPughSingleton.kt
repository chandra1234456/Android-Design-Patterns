package com.akshayashokcode.androiddesignpatterns.creational.singleton

/**
 * Bill Pugh (initialization-on-demand holder) Singleton.
 *
 * The instance lives in a nested holder object. The JVM initializes [Holder] only when it is first
 * touched (inside [getInstance]), and class initialization is thread-safe by specification, so we
 * get lazy + thread-safe creation with no `synchronized` and no `@Volatile`.
 *
 * Note: the previous version stored `Holder.INSTANCE` in a companion property, which forced the
 * holder to load as soon as the class loaded - i.e. it was eager and defeated the pattern.
 */
class BillPughSingleton private constructor() {
    private object Holder {
        val INSTANCE = BillPughSingleton()
    }

    companion object {
        fun getInstance(): BillPughSingleton = Holder.INSTANCE
    }
}
