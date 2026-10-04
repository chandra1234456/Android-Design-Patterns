package com.akshayashokcode.androiddesignpatterns.behavioral.chainofresponsibility

/** Each handler checks one rule, then passes the input along the chain. */
abstract class Validator {
    private var next: Validator? = null

    fun then(next: Validator): Validator {
        this.next = next
        return next
    }

    /** Returns the first error message, or null if every handler accepts the input. */
    fun handle(input: String): String? = validate(input) ?: next?.handle(input)

    protected abstract fun validate(input: String): String?
}

class NotEmptyValidator : Validator() {
    override fun validate(input: String) = if (input.isEmpty()) "must not be empty" else null
}

class MinLengthValidator(private val min: Int) : Validator() {
    override fun validate(input: String) = if (input.length < min) "must be at least $min chars" else null
}

class NoSpacesValidator : Validator() {
    override fun validate(input: String) = if (' ' in input) "must not contain spaces" else null
}
