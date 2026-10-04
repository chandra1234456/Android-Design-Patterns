package com.akshayashokcode.androiddesignpatterns.behavioral.visitor

/** Elements accept a visitor; new operations are added as new visitors, not by editing items. */
sealed interface CartItem {
    fun <R> accept(visitor: CartVisitor<R>): R
}

class Book(val price: Double) : CartItem {
    override fun <R> accept(visitor: CartVisitor<R>) = visitor.visitBook(this)
}

class Gadget(val price: Double) : CartItem {
    override fun <R> accept(visitor: CartVisitor<R>) = visitor.visitGadget(this)
}

interface CartVisitor<R> {
    fun visitBook(book: Book): R
    fun visitGadget(gadget: Gadget): R
}

/** One operation (tax) implemented for every item type in one place. */
class TaxVisitor : CartVisitor<Double> {
    override fun visitBook(book: Book) = 0.0
    override fun visitGadget(gadget: Gadget) = gadget.price * 0.18
}
