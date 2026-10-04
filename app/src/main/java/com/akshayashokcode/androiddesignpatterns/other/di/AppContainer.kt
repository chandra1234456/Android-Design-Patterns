package com.akshayashokcode.androiddesignpatterns.other.di

interface Logger { fun log(message: String) }

class InMemoryLogger : Logger {
    val messages = mutableListOf<String>()
    override fun log(message: String) { messages += message }
}

/** Gets its dependency from outside (constructor injection) - it never creates it itself. */
class OrderService(private val logger: Logger) {
    fun place(id: Int): String = "order-$id".also(logger::log)
}

/** Manual DI container: the one place that knows how to build things. Hilt/Koin automate this. */
class AppContainer {
    val logger: Logger by lazy { InMemoryLogger() }
    fun orderService() = OrderService(logger)
}
