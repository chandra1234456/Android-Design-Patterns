package com.akshayashokcode.androiddesignpatterns.behavioral.observer

fun interface Observer {
    fun update(message: String)
}