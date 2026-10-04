package com.akshayashokcode.androiddesignpatterns.structural.adapter

/** Target interface the app code expects. */
interface PaymentProcessor {
    fun pay(amountInRupees: Double): Boolean
}

/** Adaptee: third-party SDK we cannot modify, with an incompatible API (paise, status codes). */
class LegacyGateway {
    fun makePayment(amountInPaise: Long): Int = if (amountInPaise > 0) 200 else 400
}

/** Adapter: translates the Target API into the Adaptee's API. */
class LegacyGatewayAdapter(private val gateway: LegacyGateway) : PaymentProcessor {
    override fun pay(amountInRupees: Double): Boolean =
        gateway.makePayment(Math.round(amountInRupees * 100)) == 200
}
