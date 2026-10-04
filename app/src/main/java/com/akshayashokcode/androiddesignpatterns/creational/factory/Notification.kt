package com.akshayashokcode.androiddesignpatterns.creational.factory

/** Product interface. Callers depend only on this. */
interface Notification {
    fun send(message: String): String
}

class EmailNotification : Notification {
    override fun send(message: String) = "Email: $message"
}

class SmsNotification : Notification {
    override fun send(message: String) = "SMS: $message"
}

class PushNotification : Notification {
    override fun send(message: String) = "Push: $message"
}

enum class NotificationType { EMAIL, SMS, PUSH }

/** Simple/static Factory: centralizes creation, hides concrete classes from callers. */
object NotificationFactory {
    fun create(type: NotificationType): Notification = when (type) {
        NotificationType.EMAIL -> EmailNotification()
        NotificationType.SMS -> SmsNotification()
        NotificationType.PUSH -> PushNotification()
    } // exhaustive 'when': adding an enum value is a compile error until handled
}

/**
 * Factory Method: subclasses decide which product to create; the base class owns the workflow.
 */
abstract class Notifier {
    protected abstract fun createNotification(): Notification

    fun notifyUser(message: String): String = createNotification().send(message.trim())
}

class EmailNotifier : Notifier() {
    override fun createNotification(): Notification = EmailNotification()
}
