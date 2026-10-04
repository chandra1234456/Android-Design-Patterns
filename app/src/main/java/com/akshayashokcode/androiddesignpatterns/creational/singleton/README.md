# Singleton Pattern

## What it is?
The Singleton Pattern ensures that a class has only one instance and provides a global point of access to it.

## When to use it?
- **Configuration Management**: When you need to manage application-wide settings.
- **Resource Management**: When dealing with shared resources like thread pools or caches.
- **Logging**: When you need a single instance of a logger that can be used throughout the application.
- **Database Connections**: When managing a single instance of a database connection or repository.

## Why to use it?
- **Controlled Access**: Ensures that there is only one instance of the class, providing a controlled access point.
- **Resource Management**: Helps in managing shared resources efficiently.
- **Consistency**: Provides a consistent point of access and ensures that multiple parts of the application use the same instance.

## How to use it?
To use the Singleton Pattern, you typically create a class with a private constructor and a static method to return the instance. The static method ensures that only one instance of the class is created. Here's how the different implementations work:

1. **Classic Singleton**: A basic implementation that synchronizes the method to ensure thread safety, but can be inefficient due to synchronization overhead.
2. **Thread-Safe Singleton**: Uses double-checked locking to reduce the overhead of synchronization while still ensuring thread safety.
3. **Bill Pugh Singleton**: Uses a static inner helper class to create the instance. This method is both thread-safe and efficient, as the instance is created only when the helper class is loaded.
4. **Kotlin Singleton**: Utilizes Kotlin's `object` keyword, which ensures that the instance is created lazily and is thread-safe by default.

### Practical Example
The `AppConfig` class demonstrates how to use a singleton to manage application-wide configuration settings in an Android application.

#### AppConfig Example
The `AppConfig` class has a private constructor to prevent instantiation from outside the class. It has a static method `getInstance()` that returns the single instance of the class. The configuration settings can be set and retrieved using the methods `setConfigValue()` and `getConfigValue()`. This ensures that there is only one instance of `AppConfig` throughout the application.

#### MainActivity Example
In `MainActivity`, we access the singleton instance of `AppConfig` using `AppConfig.getInstance()`. We can set a configuration value and later retrieve it using the singleton instance. This demonstrates how the singleton pattern ensures that the same instance is used throughout the application, maintaining consistent state and behavior.

## Advantages
- **Controlled Access**: Ensures that there is only one instance of the class, providing a controlled access point.
- **Lazy Initialization**: Singleton instances are often created only when they are needed.
- **Global Access**: The single instance can be accessed globally, simplifying access to shared resources.

## Disadvantages
- **Global State**: Singletons introduce a global state into an application, which can lead to unexpected behavior if not managed correctly.
- **Difficulty in Testing**: Singletons can be difficult to test, especially if they hold state or have dependencies that need to be mocked or stubbed out.
- **Potential for Overuse**: Overusing singletons can lead to tightly coupled code, making it difficult to modify or extend.

## Best Practices
- **Lazy Initialization**: Ensure that the Singleton is lazily initialized, meaning it's created only when needed.
- **Thread Safety**: Ensure that the Singleton instance is thread-safe, particularly if it’s being accessed from multiple threads.
- **Limit Scope**: Avoid making the Singleton globally accessible if it’s not necessary. Use dependency injection to manage singletons where possible.

By following these usage considerations and best practices, you can effectively implement and utilize the Singleton Pattern in your Android applications, ensuring efficient and maintainable code.


## Implementation review & improvements
| Variant | Lazy | Thread-safe | Cost | Notes |
|---|---|---|---|---|
| `KotlinSingleton` (`object`) | Yes (on first access) | Yes (JVM class init) | None | **Preferred in Kotlin** |
| `ClassicSingleton` | Yes | Yes (`@Synchronized`) | Lock on every call | Baseline |
| `ThreadSafeSingleton` | Yes | Yes (double-checked, `@Volatile`) | Lock only on first call | Needed when constructor takes args |
| `BillPughSingleton` | Yes | Yes (holder class) | None | **Fixed**: instance was previously cached in a companion property, making it eager |
| `AppConfig` | Yes | Yes | Lock only on first call | **Fixed**: removed per-call lock and `!!`; `configValue` is `@Volatile` |

## Interview highlights
- **Why `@Volatile` in double-checked locking?** Prevents another thread seeing a partially constructed object (instruction reordering).
- **Easiest Kotlin singleton?** `object`. Use `by lazy` for lazy init with arguments.
- **Downsides:** hidden global state, hard to unit test, can leak an `Activity` `Context` - store `applicationContext` only. Prefer Dependency Injection (Hilt `@Singleton`).
- **Can reflection/serialization break it?** Yes for private-constructor classes; `enum`/`object` are safest.
