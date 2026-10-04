package com.akshayashokcode.androiddesignpatterns

import com.akshayashokcode.androiddesignpatterns.behavioral.observer.ConcreteSubject
import com.akshayashokcode.androiddesignpatterns.behavioral.observer.Observer
import com.akshayashokcode.androiddesignpatterns.behavioral.strategy.*
import com.akshayashokcode.androiddesignpatterns.creational.builder.httpRequest
import com.akshayashokcode.androiddesignpatterns.creational.factory.*
import com.akshayashokcode.androiddesignpatterns.creational.singleton.AppConfig
import com.akshayashokcode.androiddesignpatterns.creational.singleton.BillPughSingleton
import com.akshayashokcode.androiddesignpatterns.other.repository.*
import com.akshayashokcode.androiddesignpatterns.structural.adapter.*
import com.akshayashokcode.androiddesignpatterns.structural.decorator.*
import org.junit.Assert.*
import org.junit.Test

class PatternsTest {
    @Test fun singletons_returnSameInstance() {
        assertSame(AppConfig.getInstance(), AppConfig.getInstance())
        assertSame(BillPughSingleton.getInstance(), BillPughSingleton.getInstance())
    }

    @Test fun observer_selfRemovalDuringNotifyIsSafe() {
        val subject = ConcreteSubject()
        val received = mutableListOf<String>()
        lateinit var once: Observer
        once = Observer { received += "once:$it"; subject.removeObserver(once) }
        subject.registerObserver(once)
        subject.registerObserver(once) // duplicate ignored
        subject.setMessage("a")
        subject.setMessage("b")
        assertEquals(listOf("once:a"), received)
    }

    @Test fun builder_buildsAndValidates() {
        val r = httpRequest("https://x.dev") { method("post"); body("{}"); header("A", "1") }
        assertEquals("POST", r.method)
        assertEquals(mapOf("A" to "1"), r.headers)
        assertThrows(IllegalArgumentException::class.java) { httpRequest("https://x.dev") { body("{}") } }
    }

    @Test fun factory_createsExpectedTypes() {
        assertTrue(NotificationFactory.create(NotificationType.SMS) is SmsNotification)
        assertEquals("Email: hi", EmailNotifier().notifyUser("  hi "))
    }

    @Test fun strategy_isSwappableAtRuntime() {
        val c = Checkout()
        assertEquals(100.0, c.total(100.0), 0.0)
        c.strategy = PercentageDiscount(10.0)
        assertEquals(90.0, c.total(100.0), 0.0)
        c.strategy = FlatDiscount(500.0)
        assertEquals(0.0, c.total(100.0), 0.0)
    }

    @Test fun decorators_stack() {
        val ds = ReverseDecorator(UpperCaseOnWriteDecorator(InMemoryDataSource()))
        ds.write("abc")
        assertEquals("abc".uppercase(), ds.read())
    }

    @Test fun adapter_convertsUnits() {
        assertTrue(LegacyGatewayAdapter(LegacyGateway()).pay(1.5))
        assertFalse(LegacyGatewayAdapter(LegacyGateway()).pay(0.0))
    }

    @Test fun repository_cachesRemoteResult() {
        val store = mutableMapOf<Int, User>()
        var remoteCalls = 0
        val repo = DefaultUserRepository(
            object : UserLocalSource {
                override fun get(id: Int) = store[id]
                override fun save(user: User) { store[user.id] = user }
            },
            object : UserRemoteSource {
                override fun fetch(id: Int): User? { remoteCalls++; return User(id, "Ann") }
            }
        )
        repo.getUser(1); repo.getUser(1)
        assertEquals(1, remoteCalls)
    }
}
