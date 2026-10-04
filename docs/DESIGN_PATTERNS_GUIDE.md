# Design Patterns Guide for Android (plain-language edition)

A design pattern is a **proven recipe for a problem that keeps coming back**. You are not forced to use one; you reach for it when you recognise the problem.

How to read each entry:
- **Idea** - one sentence, everyday analogy.
- **Use it when** - the situation that should make you think of it.
- **Android example** - where you will really meet it.
- **Code** - the file in this repo.

**Android importance:** ⭐⭐⭐ use constantly / must know for interviews, ⭐⭐ common, ⭐ occasional.

---

## The 3 categories (and the question each answers)

| Category | Question it answers | Patterns |
|---|---|---|
| **Creational** | *How do I create objects cleanly?* | Singleton, Builder, Factory, Abstract Factory, Prototype |
| **Structural** | *How do I put objects together?* | Adapter, Decorator, Proxy, Facade, Composite, Flyweight, Bridge |
| **Behavioral** | *How do objects talk and share work?* | Observer, Strategy, Command, State, Chain of Responsibility, Mediator, Memento, Iterator, Visitor, Template Method |
| **Architectural / other** | *How do I organise the whole app?* | Repository, Dependency Injection |

## The Android "top 8" - learn these first
1. **Observer** ⭐⭐⭐ - LiveData / Flow / listeners
2. **Builder** ⭐⭐⭐ - AlertDialog, Notification, Retrofit, OkHttp
3. **Singleton** ⭐⭐⭐ - Application-wide objects (prefer DI)
4. **Dependency Injection** ⭐⭐⭐ - Hilt / Koin
5. **Repository** ⭐⭐⭐ - data layer in MVVM
6. **Adapter** ⭐⭐⭐ - RecyclerView.Adapter
7. **Factory** ⭐⭐⭐ - ViewModelProvider.Factory
8. **Strategy** ⭐⭐ - swap behaviour (layout managers, sort rules)

---

## 1. Creational patterns

### Singleton ⭐⭐⭐ - `creational/singleton`
- **Idea:** only one instance exists, everyone shares it (one school principal).
- **Use it when:** a single shared resource is needed - app config, database, HTTP client, logger.
- **Android example:** `Room` database instance, Retrofit client, Hilt `@Singleton`.
- **Careful:** hidden global state hurts testing; never store an `Activity` in it.

### Builder ⭐⭐⭐ - `creational/builder`
- **Idea:** build a complex object step by step (ordering a custom burger).
- **Use it when:** a class has many optional settings, or must be valid before use.
- **Android example:** `AlertDialog.Builder`, `NotificationCompat.Builder`, `Retrofit.Builder`.

### Factory / Factory Method ⭐⭐⭐ - `creational/factory`
- **Idea:** ask a factory for the object instead of calling `new` yourself (order "a vehicle", the factory decides car or bike).
- **Use it when:** the exact class depends on runtime input, or constructors need special arguments.
- **Android example:** `ViewModelProvider.Factory`, `Fragment.newInstance()`.

### Abstract Factory ⭐ - `creational/abstractfactory`
- **Idea:** a factory that makes a *matching set* of objects (furniture shop: modern chair + modern table).
- **Use it when:** parts must belong together - light/dark theme widgets, per-platform UI, per-environment (dev/prod) services.
- **Android example:** theming widget families, build-flavor service sets.

### Prototype ⭐ - `creational/prototype`
- **Idea:** create a new object by copying an existing one (photocopy a form, then edit).
- **Use it when:** creating from scratch is costly, or you need a modified copy of a template.
- **Android example:** Kotlin `data class.copy()`, `Intent(intent)`, `Bundle.clone()`.
- **Careful:** copy nested mutable objects too (deep copy).

---

## 2. Structural patterns

### Adapter ⭐⭐⭐ - `structural/adapter`
- **Idea:** a travel plug adapter - makes incompatible interfaces work together.
- **Use it when:** you must use a library/class whose API does not match what your code expects.
- **Android example:** `RecyclerView.Adapter`, wrapping a legacy SDK.

### Decorator ⭐⭐ - `structural/decorator`
- **Idea:** add toppings to a coffee - wrap an object to add behaviour, same interface.
- **Use it when:** you want optional extra features without a subclass for each combination.
- **Android example:** `ContextWrapper`, Java I/O streams.

### Proxy ⭐⭐ - `structural/proxy`
- **Idea:** a receptionist - stands in front of the real object and controls access.
- **Use it when:** lazy loading, caching, access control, logging around an expensive object.
- **Android example:** image loaders caching bitmaps, Retrofit's dynamic proxy for your API interface, `by lazy`.

### Facade ⭐⭐ - `structural/facade`
- **Idea:** a hotel concierge - one simple desk for many back-office services.
- **Use it when:** a feature needs several classes in a fixed order and callers should not care.
- **Android example:** a `UploadManager`, a `PaymentSdk` wrapper, `WorkManager` hiding scheduler details.

### Composite ⭐⭐ - `structural/composite`
- **Idea:** folders contain files *and* folders - treat one item and a group the same.
- **Use it when:** data is a tree (menus, comments with replies, UI hierarchies).
- **Android example:** `View` / `ViewGroup`, Compose UI tree, nested comment threads.

### Flyweight ⭐ - `structural/flyweight`
- **Idea:** share common data instead of duplicating it (one font glyph drawn many times).
- **Use it when:** thousands of similar objects waste memory.
- **Android example:** `RecyclerView` view recycling, `LruCache` of bitmaps, `Integer.valueOf` cache.

### Bridge ⭐ - `structural/bridge`
- **Idea:** split "what" from "how" so they vary independently (remote control vs TV brand).
- **Use it when:** two dimensions of variation would otherwise multiply classes.
- **Android example:** alert type x delivery channel, `Drawer` abstraction x platform implementation.

---

## 3. Behavioral patterns

### Observer ⭐⭐⭐ - `behavioral/observer`
- **Idea:** subscribe to a YouTube channel - get notified when something changes.
- **Use it when:** several parts must react when one thing changes.
- **Android example:** `LiveData`, `StateFlow`, `OnClickListener`, `BroadcastReceiver`.
- **Careful:** unregister to avoid leaks.

### Strategy ⭐⭐ - `behavioral/strategy`
- **Idea:** choose a route in a maps app - same goal, swappable method.
- **Use it when:** you have `if/else` choosing between algorithms (discounts, sorting, validation).
- **Android example:** `LayoutManager`, `Comparator`, caching policies.

### Command ⭐⭐ - `behavioral/command`
- **Idea:** a restaurant order slip - an action wrapped as an object that can be queued, logged, undone.
- **Use it when:** undo/redo, task queues, retry, macro actions.
- **Android example:** `Runnable`/`Handler.post`, `WorkManager` work requests, editor undo.

### State ⭐⭐ - `behavioral/state`
- **Idea:** a traffic light - behaviour changes with the current state.
- **Use it when:** an object acts differently depending on its status (player, order, login flow).
- **Android example:** media player, `sealed class UiState` (Loading / Success / Error) in MVVM.

### Chain of Responsibility ⭐⭐ - `behavioral/chainofresponsibility`
- **Idea:** complaint passed from support agent to manager until someone handles it.
- **Use it when:** a request goes through several optional checks/handlers in order.
- **Android example:** OkHttp interceptors, touch-event dispatch (`onTouchEvent` bubbling), form validation.

### Mediator ⭐ - `behavioral/mediator`
- **Idea:** an air-traffic tower - planes talk to the tower, not each other.
- **Use it when:** many components talk to each other and the wiring becomes a tangle.
- **Android example:** a `ViewModel` coordinating several UI widgets, a shared event bus.

### Memento ⭐ - `behavioral/memento`
- **Idea:** a save-game slot - snapshot state to restore later.
- **Use it when:** undo, drafts, restoring state without exposing internals.
- **Android example:** `onSaveInstanceState` / `Bundle`, `SavedStateHandle`, editor undo history.

### Iterator ⭐ - `behavioral/iterator`
- **Idea:** flipping pages of a book one by one.
- **Use it when:** you need to walk a collection (or paged data) without knowing how it is stored.
- **Android example:** Kotlin `for` loops/`Iterator`, `Cursor` rows, Paging 3 pages.

### Visitor ⭐ - `behavioral/visitor`
- **Idea:** a tax inspector visits different shops, doing a different check at each type.
- **Use it when:** you need new operations over a fixed set of item types without editing them.
- **Android example:** walking an AST/DOM, KSP/annotation processors; in Kotlin often replaced by `when` on a `sealed class`.

### Template Method ⭐⭐ - `behavioral/templatemethod`
- **Idea:** a recipe with fixed steps; you choose the ingredients.
- **Use it when:** several classes share the same workflow but differ in a few steps.
- **Android example:** `Activity`/`Fragment` lifecycle (`onCreate`, `onStart`...), `AsyncTask`-style base classes, `RecyclerView.Adapter` callbacks.

---

## 4. Architectural patterns

### Repository ⭐⭐⭐ - `other/repository`
- **Idea:** a librarian - you ask for a book, not where it is stored.
- **Use it when:** data comes from several places (API, database, cache).
- **Android example:** UI -> ViewModel -> Repository -> Room / Retrofit.

### Dependency Injection ⭐⭐⭐ - `other/di`
- **Idea:** a chef is *given* ingredients instead of growing them.
- **Use it when:** classes create their own dependencies, making them hard to test or swap.
- **Android example:** Hilt, Koin, constructor injection of repositories into ViewModels.

---

## Quick chooser: "I have this problem..."

| Problem | Pattern |
|---|---|
| Many optional constructor params | Builder |
| Need exactly one shared instance | Singleton (or DI scope) |
| Class decided at runtime | Factory |
| UI/data must change when something updates | Observer |
| Many `if/else` choosing an algorithm | Strategy |
| Third-party API doesn't fit my interface | Adapter |
| Add features without subclass explosion | Decorator |
| Expensive object - delay or cache it | Proxy |
| Complex subsystem, want a simple call | Facade |
| Tree of items/groups | Composite |
| Need undo/redo or queued actions | Command / Memento |
| Behaviour depends on status | State |
| Same steps, different details | Template Method |
| Pipeline of checks | Chain of Responsibility |
| Data from API + DB + cache | Repository |
| Hard-to-test hard-coded dependencies | Dependency Injection |

## Common mistakes
- Using a pattern "because it's cool" - start with the simplest code, refactor into a pattern when the pain appears.
- Singleton for everything - prefer injecting.
- Forgetting lifecycle (Observer leaks, Activity stored in Singleton).
- In Kotlin, remember language features that replace patterns: `object` (Singleton), lambdas (Strategy/Command), `by` (Decorator), `sealed` + `when` (State/Visitor), `data class.copy()` (Prototype), `by lazy` (lazy Proxy).
