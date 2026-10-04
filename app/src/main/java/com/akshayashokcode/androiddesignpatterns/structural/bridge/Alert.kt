package com.akshayashokcode.androiddesignpatterns.structural.bridge

/** Implementation side: HOW a message is delivered. */
interface MessageSender { fun send(text: String): String }
class EmailSender : MessageSender { override fun send(text: String) = "email:$text" }
class SmsSender : MessageSender { override fun send(text: String) = "sms:$text" }

/** Abstraction side: WHAT kind of alert it is. Holds a sender (the "bridge"). */
abstract class Alert(protected val sender: MessageSender) {
    abstract fun notify(text: String): String
}

class UrgentAlert(sender: MessageSender) : Alert(sender) {
    override fun notify(text: String) = sender.send("URGENT: $text")
}

class NormalAlert(sender: MessageSender) : Alert(sender) {
    override fun notify(text: String) = sender.send(text)
}
// 2 alert kinds x 2 senders = 4 combinations from 4 classes; adding a PushSender adds 1 class, not 2.
