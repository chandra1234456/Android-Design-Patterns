package com.akshayashokcode.androiddesignpatterns.behavioral.mediator

interface ChatMediator {
    fun join(user: User)
    fun send(from: User, message: String)
}

/** Mediator: users talk only to the room, never to each other directly. */
class ChatRoom : ChatMediator {
    private val users = mutableListOf<User>()

    override fun join(user: User) { users += user }

    override fun send(from: User, message: String) {
        users.filter { it !== from }.forEach { it.receive(from.name, message) }
    }
}

class User(val name: String, private val mediator: ChatMediator) {
    val inbox = mutableListOf<String>()
    fun say(message: String) = mediator.send(this, message)
    fun receive(from: String, message: String) { inbox += "$from: $message" }
}
