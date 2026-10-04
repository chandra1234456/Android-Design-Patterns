package com.akshayashokcode.androiddesignpatterns.creational.abstractfactory

interface ThemedButton { fun render(): String }
interface ThemedDialog { fun render(): String }

/** Abstract Factory: creates a whole FAMILY of matching UI parts. */
interface UiFactory {
    fun createButton(): ThemedButton
    fun createDialog(): ThemedDialog
}

object LightUiFactory : UiFactory {
    override fun createButton() = object : ThemedButton { override fun render() = "light-button" }
    override fun createDialog() = object : ThemedDialog { override fun render() = "light-dialog" }
}

object DarkUiFactory : UiFactory {
    override fun createButton() = object : ThemedButton { override fun render() = "dark-button" }
    override fun createDialog() = object : ThemedDialog { override fun render() = "dark-dialog" }
}

/** Pick the family once; the rest of the app never mixes light and dark parts. */
fun uiFactoryFor(darkMode: Boolean): UiFactory = if (darkMode) DarkUiFactory else LightUiFactory
