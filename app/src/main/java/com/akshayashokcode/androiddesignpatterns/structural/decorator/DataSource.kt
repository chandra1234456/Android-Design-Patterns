package com.akshayashokcode.androiddesignpatterns.structural.decorator

/** Component interface. */
interface DataSource {
    fun write(data: String)
    fun read(): String
}

class InMemoryDataSource : DataSource {
    private var stored = ""
    override fun write(data: String) { stored = data }
    override fun read() = stored
}

/** Base decorator: same interface, wraps another one (composition over inheritance). */
abstract class DataSourceDecorator(private val wrappee: DataSource) : DataSource by wrappee

/** Adds behaviour around write/read without touching the wrapped class. */
class ReverseDecorator(private val inner: DataSource) : DataSourceDecorator(inner) {
    override fun write(data: String) = inner.write(data.reversed())
    override fun read() = inner.read().reversed()
}

class UpperCaseOnWriteDecorator(private val inner: DataSource) : DataSourceDecorator(inner) {
    override fun write(data: String) = inner.write(data.uppercase())
}
