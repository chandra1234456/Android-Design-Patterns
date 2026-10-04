package com.akshayashokcode.androiddesignpatterns.structural.flyweight

import java.util.concurrent.ConcurrentHashMap

/** Heavy, shareable (intrinsic) data. Immutable so sharing is safe. */
class Icon(val name: String)

/** Flyweight factory: hands out ONE Icon per name, no matter how many rows ask for it. */
class IconFactory {
    private val cache = ConcurrentHashMap<String, Icon>()
    fun get(name: String): Icon = cache.getOrPut(name) { Icon(name) }
    val size: Int get() = cache.size
}
