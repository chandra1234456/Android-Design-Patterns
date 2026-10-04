package com.akshayashokcode.androiddesignpatterns

import com.akshayashokcode.androiddesignpatterns.behavioral.chainofresponsibility.*
import com.akshayashokcode.androiddesignpatterns.behavioral.command.*
import com.akshayashokcode.androiddesignpatterns.behavioral.iterator.PageIterator
import com.akshayashokcode.androiddesignpatterns.behavioral.mediator.ChatRoom
import com.akshayashokcode.androiddesignpatterns.behavioral.mediator.User
import com.akshayashokcode.androiddesignpatterns.behavioral.memento.Editor
import com.akshayashokcode.androiddesignpatterns.behavioral.memento.History
import com.akshayashokcode.androiddesignpatterns.behavioral.state.Player
import com.akshayashokcode.androiddesignpatterns.behavioral.state.PlayerState
import com.akshayashokcode.androiddesignpatterns.behavioral.templatemethod.UserSyncTask
import com.akshayashokcode.androiddesignpatterns.behavioral.visitor.*
import com.akshayashokcode.androiddesignpatterns.creational.abstractfactory.uiFactoryFor
import com.akshayashokcode.androiddesignpatterns.creational.prototype.Report
import com.akshayashokcode.androiddesignpatterns.other.di.AppContainer
import com.akshayashokcode.androiddesignpatterns.other.di.InMemoryLogger
import com.akshayashokcode.androiddesignpatterns.structural.bridge.*
import com.akshayashokcode.androiddesignpatterns.structural.composite.Container
import com.akshayashokcode.androiddesignpatterns.structural.composite.Label
import com.akshayashokcode.androiddesignpatterns.structural.facade.UploadFacade
import com.akshayashokcode.androiddesignpatterns.structural.flyweight.IconFactory
import com.akshayashokcode.androiddesignpatterns.structural.proxy.CachingImageProxy
import com.akshayashokcode.androiddesignpatterns.structural.proxy.RealImageLoader
import org.junit.Assert.*
import org.junit.Test

class MorePatternsTest {
    @Test fun prototype_copyIsIndependent() {
        val original = Report("Q1", mutableListOf("intro"))
        val copy = original.copyOf().also { it.sections += "extra" }
        assertEquals(1, original.sections.size)
        assertEquals(2, copy.sections.size)
    }

    @Test fun abstractFactory_returnsMatchingFamily() {
        assertEquals("dark-button", uiFactoryFor(true).createButton().render())
        assertEquals("light-dialog", uiFactoryFor(false).createDialog().render())
    }

    @Test fun proxy_downloadsOnlyOnce() {
        val real = RealImageLoader()
        val proxy = CachingImageProxy(real)
        proxy.load("a.png"); proxy.load("a.png")
        assertEquals(1, real.downloads)
    }

    @Test fun composite_countsWholeTree() {
        val tree = Container("root").add(Label("a")).add(Container("inner").add(Label("b")))
        assertEquals(4, tree.count())
        assertTrue(tree.render().contains("Label(b)"))
    }

    @Test fun flyweight_sharesInstances() {
        val f = IconFactory()
        assertSame(f.get("star"), f.get("star"))
        assertEquals(1, f.size)
    }

    @Test fun facade_hidesSubsystems() {
        assertEquals("uploaded a.txt.zip with token-123", UploadFacade().upload("a.txt"))
    }

    @Test fun bridge_combinesAbstractionAndImplementation() {
        assertEquals("sms:URGENT: hi", UrgentAlert(SmsSender()).notify("hi"))
        assertEquals("email:hi", NormalAlert(EmailSender()).notify("hi"))
    }

    @Test fun command_undo() {
        val editor = TextEditor()
        val history = CommandHistory()
        history.run(AppendCommand(editor, "ab"))
        history.run(AppendCommand(editor, "cd"))
        history.undo()
        assertEquals("ab", editor.text.toString())
    }

    @Test fun state_transitions() {
        val p = Player()
        p.tap(); assertSame(PlayerState.Playing, p.state)
        p.tap(); assertSame(PlayerState.Paused, p.state)
        p.tap(); assertSame(PlayerState.Playing, p.state)
    }

    @Test fun chain_returnsFirstError() {
        val chain = NotEmptyValidator()
        chain.then(MinLengthValidator(4)).then(NoSpacesValidator())
        assertEquals("must not be empty", chain.handle(""))
        assertEquals("must be at least 4 chars", chain.handle("ab"))
        assertEquals("must not contain spaces", chain.handle("ab cd"))
        assertNull(chain.handle("abcd"))
    }

    @Test fun mediator_relaysToOthersOnly() {
        val room = ChatRoom()
        val a = User("A", room); val b = User("B", room)
        room.join(a); room.join(b)
        a.say("hi")
        assertEquals(listOf("A: hi"), b.inbox)
        assertTrue(a.inbox.isEmpty())
    }

    @Test fun memento_restoresSnapshot() {
        val editor = Editor(); val history = History()
        editor.content = "v1"; history.push(editor.save())
        editor.content = "v2"
        editor.restore(history.pop()!!)
        assertEquals("v1", editor.content)
    }

    @Test fun iterator_pages() {
        val pages = PageIterator(listOf(1, 2, 3, 4, 5), 2).asSequence().toList()
        assertEquals(listOf(listOf(1, 2), listOf(3, 4), listOf(5)), pages)
    }

    @Test fun visitor_computesTax() {
        val tax = listOf<CartItem>(Book(100.0), Gadget(100.0)).sumOf { it.accept(TaxVisitor()) }
        assertEquals(18.0, tax, 0.001)
    }

    @Test fun templateMethod_runsStepsInOrder() {
        val task = UserSyncTask()
        assertEquals(listOf("start", "fetched users", "saved", "done"), task.run())
    }

    @Test fun di_injectsSharedDependency() {
        val container = AppContainer()
        container.orderService().place(7)
        assertEquals(listOf("order-7"), (container.logger as InMemoryLogger).messages)
    }
}
