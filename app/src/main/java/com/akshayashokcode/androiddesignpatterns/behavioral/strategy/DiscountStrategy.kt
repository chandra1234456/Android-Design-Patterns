package com.akshayashokcode.androiddesignpatterns.behavioral.strategy

/** Strategy: an interchangeable algorithm. A fun interface lets callers pass a lambda. */
fun interface DiscountStrategy {
    fun apply(amount: Double): Double
}

object NoDiscount : DiscountStrategy {
    override fun apply(amount: Double) = amount
}

class PercentageDiscount(private val percent: Double) : DiscountStrategy {
    init { require(percent in 0.0..100.0) { "percent must be 0..100" } }
    override fun apply(amount: Double) = amount * (1 - percent / 100)
}

class FlatDiscount(private val off: Double) : DiscountStrategy {
    override fun apply(amount: Double) = (amount - off).coerceAtLeast(0.0)
}

/** Context: delegates the varying behaviour; strategy can be swapped at runtime. */
class Checkout(var strategy: DiscountStrategy = NoDiscount) {
    fun total(amount: Double): Double = strategy.apply(amount)
}
