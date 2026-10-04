package com.akshayashokcode.androiddesignpatterns.demo

import com.akshayashokcode.androiddesignpatterns.behavioral.chainofresponsibility.MinLengthValidator
import com.akshayashokcode.androiddesignpatterns.behavioral.chainofresponsibility.NoSpacesValidator
import com.akshayashokcode.androiddesignpatterns.behavioral.chainofresponsibility.NotEmptyValidator
import com.akshayashokcode.androiddesignpatterns.behavioral.command.AppendCommand
import com.akshayashokcode.androiddesignpatterns.behavioral.command.CommandHistory
import com.akshayashokcode.androiddesignpatterns.behavioral.command.TextEditor
import com.akshayashokcode.androiddesignpatterns.behavioral.iterator.PageIterator
import com.akshayashokcode.androiddesignpatterns.behavioral.mediator.ChatRoom
import com.akshayashokcode.androiddesignpatterns.behavioral.mediator.User
import com.akshayashokcode.androiddesignpatterns.behavioral.memento.Editor
import com.akshayashokcode.androiddesignpatterns.behavioral.memento.History
import com.akshayashokcode.androiddesignpatterns.behavioral.observer.ConcreteSubject
import com.akshayashokcode.androiddesignpatterns.behavioral.observer.Observer
import com.akshayashokcode.androiddesignpatterns.behavioral.state.Player
import com.akshayashokcode.androiddesignpatterns.behavioral.strategy.Checkout
import com.akshayashokcode.androiddesignpatterns.behavioral.strategy.FlatDiscount
import com.akshayashokcode.androiddesignpatterns.behavioral.strategy.PercentageDiscount
import com.akshayashokcode.androiddesignpatterns.behavioral.templatemethod.UserSyncTask
import com.akshayashokcode.androiddesignpatterns.behavioral.visitor.Book
import com.akshayashokcode.androiddesignpatterns.behavioral.visitor.CartItem
import com.akshayashokcode.androiddesignpatterns.behavioral.visitor.Gadget
import com.akshayashokcode.androiddesignpatterns.behavioral.visitor.TaxVisitor
import com.akshayashokcode.androiddesignpatterns.creational.abstractfactory.uiFactoryFor
import com.akshayashokcode.androiddesignpatterns.creational.builder.httpRequest
import com.akshayashokcode.androiddesignpatterns.creational.factory.EmailNotifier
import com.akshayashokcode.androiddesignpatterns.creational.factory.NotificationFactory
import com.akshayashokcode.androiddesignpatterns.creational.factory.NotificationType
import com.akshayashokcode.androiddesignpatterns.creational.prototype.Report
import com.akshayashokcode.androiddesignpatterns.creational.singleton.AppConfig
import com.akshayashokcode.androiddesignpatterns.creational.singleton.KotlinSingleton
import com.akshayashokcode.androiddesignpatterns.other.di.AppContainer
import com.akshayashokcode.androiddesignpatterns.other.di.InMemoryLogger
import com.akshayashokcode.androiddesignpatterns.other.repository.DefaultUserRepository
import com.akshayashokcode.androiddesignpatterns.other.repository.User as RepoUser
import com.akshayashokcode.androiddesignpatterns.other.repository.UserLocalSource
import com.akshayashokcode.androiddesignpatterns.other.repository.UserRemoteSource
import com.akshayashokcode.androiddesignpatterns.structural.adapter.LegacyGateway
import com.akshayashokcode.androiddesignpatterns.structural.adapter.LegacyGatewayAdapter
import com.akshayashokcode.androiddesignpatterns.structural.bridge.EmailSender
import com.akshayashokcode.androiddesignpatterns.structural.bridge.NormalAlert
import com.akshayashokcode.androiddesignpatterns.structural.bridge.SmsSender
import com.akshayashokcode.androiddesignpatterns.structural.bridge.UrgentAlert
import com.akshayashokcode.androiddesignpatterns.structural.composite.Container
import com.akshayashokcode.androiddesignpatterns.structural.composite.Label
import com.akshayashokcode.androiddesignpatterns.structural.decorator.InMemoryDataSource
import com.akshayashokcode.androiddesignpatterns.structural.decorator.ReverseDecorator
import com.akshayashokcode.androiddesignpatterns.structural.decorator.UpperCaseOnWriteDecorator
import com.akshayashokcode.androiddesignpatterns.structural.facade.UploadFacade
import com.akshayashokcode.androiddesignpatterns.structural.flyweight.IconFactory
import com.akshayashokcode.androiddesignpatterns.structural.proxy.CachingImageProxy
import com.akshayashokcode.androiddesignpatterns.structural.proxy.RealImageLoader

/** One screen entry: what the pattern is for, and a function that runs it and returns a readable log. */
class PatternDemo(
    val name: String,
    val category: String,
    val whenToUse: String,
    val run: () -> String
)

/** Every demo runs the real pattern classes from this repo - nothing is faked. */
object PatternDemos {
    private const val C = "Creational"
    private const val S = "Structural"
    private const val B = "Behavioral"
    private const val O = "Architectural"

    val all: List<PatternDemo> = listOf(
        PatternDemo("Singleton", C, "One shared instance: app config, database, logger.") {
            AppConfig.getInstance().configValue = "dark_mode=true"
            val other = AppConfig.getInstance()
            "Set value through reference #1.\nRead through reference #2: ${other.configValue}\n" +
                "Same object? ${AppConfig.getInstance() === other}\nKotlin object: $KotlinSingleton"
        },
        PatternDemo("Builder", C, "Many optional settings, validated before use (dialogs, Retrofit, notifications).") {
            val r = httpRequest("https://api.example.com/users") {
                method("post"); header("Accept", "application/json"); body("{}"); timeout(5_000)
            }
            "${r.method} ${r.url}\nheaders=${r.headers}\nbody=${r.body}\ntimeout=${r.timeoutMs}ms"
        },
        PatternDemo("Factory", C, "Class chosen at runtime (ViewModelProvider.Factory).") {
            NotificationType.values().joinToString("\n") {
                NotificationFactory.create(it).send("Hello")
            } + "\nFactory Method: " + EmailNotifier().notifyUser("  trimmed by template  ")
        },
        PatternDemo("Abstract Factory", C, "A matching family of parts (light/dark theme widgets).") {
            listOf(false, true).joinToString("\n") {
                val f = uiFactoryFor(it)
                "darkMode=$it -> ${f.createButton().render()}, ${f.createDialog().render()}"
            }
        },
        PatternDemo("Prototype", C, "Copy a template, then change only what differs.") {
            val template = Report("Monthly", mutableListOf("Summary"))
            val copy = template.copyOf().also { it.sections += "Sales" }
            "Template: $template\nCopy:     $copy\nTemplate unchanged: ${template.sections.size == 1}"
        },

        PatternDemo("Adapter", S, "Make an incompatible SDK fit your interface (RecyclerView.Adapter).") {
            val p = LegacyGatewayAdapter(LegacyGateway())
            "pay(Rs 150.50) -> ${p.pay(150.50)}\npay(Rs 0) -> ${p.pay(0.0)}\n(adapter converts rupees to paise and 200/400 codes to Boolean)"
        },
        PatternDemo("Decorator", S, "Add behaviour by wrapping, without subclass explosion.") {
            val ds = ReverseDecorator(UpperCaseOnWriteDecorator(InMemoryDataSource()))
            ds.write("hello")
            "write(\"hello\") then read() -> ${ds.read()}"
        },
        PatternDemo("Proxy", S, "Cache or lazy-load an expensive object (image loading).") {
            val real = RealImageLoader()
            val proxy = CachingImageProxy(real)
            repeat(3) { proxy.load("cat.png") }
            "Asked for cat.png 3 times\nReal downloads: ${real.downloads}"
        },
        PatternDemo("Facade", S, "One simple call over several subsystems (upload manager).") {
            UploadFacade().upload("photo.jpg")
        },
        PatternDemo("Composite", S, "Tree where a leaf and a group are treated alike (View/ViewGroup).") {
            val ui = Container("Screen")
                .add(Label("Title"))
                .add(Container("Form").add(Label("Name")).add(Label("Email")))
            "${ui.render()}\nTotal nodes: ${ui.count()}"
        },
        PatternDemo("Flyweight", S, "Share identical heavy objects across many items (list icons).") {
            val icons = IconFactory()
            val rows = List(1_000) { icons.get(if (it % 2 == 0) "home" else "star") }
            "Rows: ${rows.size}\nIcon objects created: ${icons.size}"
        },
        PatternDemo("Bridge", S, "Two things vary independently (alert type x channel).") {
            listOf(
                UrgentAlert(SmsSender()).notify("Server down"),
                NormalAlert(EmailSender()).notify("Weekly report")
            ).joinToString("\n")
        },

        PatternDemo("Observer", B, "Many parts react when one thing changes (LiveData/Flow).") {
            val log = mutableListOf<String>()
            val subject = ConcreteSubject()
            subject.registerObserver(Observer { log += "Screen A got: $it" })
            val b = Observer { log += "Screen B got: $it" }
            subject.registerObserver(b)
            subject.setMessage("Order shipped")
            subject.removeObserver(b)
            subject.setMessage("Order delivered")
            log.joinToString("\n")
        },
        PatternDemo("Strategy", B, "Swap an algorithm at runtime (discounts, sorting).") {
            val c = Checkout()
            val a = c.total(1000.0)
            c.strategy = PercentageDiscount(10.0)
            val b = c.total(1000.0)
            c.strategy = FlatDiscount(250.0)
            "No discount: $a\n10% off: $b\nFlat 250 off: ${c.total(1000.0)}"
        },
        PatternDemo("Command", B, "Actions as objects: undo, queue, retry.") {
            val editor = TextEditor()
            val history = CommandHistory()
            history.run(AppendCommand(editor, "Hello "))
            history.run(AppendCommand(editor, "World"))
            val before = editor.text.toString()
            history.undo()
            "After 2 commands: \"$before\"\nAfter undo: \"${editor.text}\""
        },
        PatternDemo("State", B, "Behaviour depends on status (player, order, UiState).") {
            val p = Player()
            (1..4).joinToString("\n") { p.tap(); "tap #$it -> ${p.state.label}" }
        },
        PatternDemo("Chain of Responsibility", B, "Pipeline of checks/handlers (OkHttp interceptors, validation).") {
            val chain = NotEmptyValidator()
            chain.then(MinLengthValidator(6)).then(NoSpacesValidator())
            listOf("", "abc", "abc def gh", "secret1").joinToString("\n") {
                "\"$it\" -> ${chain.handle(it) ?: "OK"}"
            }
        },
        PatternDemo("Mediator", B, "Components talk through one coordinator (chat room, ViewModel).") {
            val room = ChatRoom()
            val ann = User("Ann", room); val raj = User("Raj", room); val lee = User("Lee", room)
            listOf(ann, raj, lee).forEach(room::join)
            ann.say("Hi all")
            "Raj inbox: ${raj.inbox}\nLee inbox: ${lee.inbox}\nAnn inbox: ${ann.inbox}"
        },
        PatternDemo("Memento", B, "Snapshot and restore state (undo, onSaveInstanceState).") {
            val e = Editor(); val h = History()
            e.content = "Draft 1"; h.push(e.save())
            e.content = "Draft 2 (oops)"
            val broken = e.content
            h.pop()?.let(e::restore)
            "Before restore: $broken\nAfter restore: ${e.content}"
        },
        PatternDemo("Iterator", B, "Walk data without knowing how it is stored (paging).") {
            PageIterator((1..7).toList(), 3).asSequence()
                .mapIndexed { i, page -> "Page ${i + 1}: $page" }.joinToString("\n")
        },
        PatternDemo("Visitor", B, "New operations over fixed item types without editing them.") {
            val cart = listOf<CartItem>(Book(300.0), Gadget(1000.0))
            "Tax per item: ${cart.map { it.accept(TaxVisitor()) }}"
        },
        PatternDemo("Template Method", B, "Same workflow, different steps (lifecycle, sync tasks).") {
            UserSyncTask().run().joinToString(" -> ")
        },

        PatternDemo("Repository", O, "One clean data API over network + database + cache.") {
            val cache = mutableMapOf<Int, RepoUser>()
            var network = 0
            val repo = DefaultUserRepository(
                object : UserLocalSource {
                    override fun get(id: Int) = cache[id]
                    override fun save(user: RepoUser) { cache[user.id] = user }
                },
                object : UserRemoteSource {
                    override fun fetch(id: Int): RepoUser? { network++; return RepoUser(id, "Ann") }
                }
            )
            val first = repo.getUser(1); val second = repo.getUser(1)
            "1st call: $first (from network)\n2nd call: $second (from cache)\nNetwork calls: $network"
        },
        PatternDemo("Dependency Injection", O, "Give a class its dependencies instead of creating them (Hilt).") {
            val container = AppContainer()
            val service = container.orderService()
            service.place(42); service.place(43)
            "Logger received: ${(container.logger as InMemoryLogger).messages}"
        }
    )
}
