package com.akshayashokcode.androiddesignpatterns.other.repository

data class User(val id: Int, val name: String)

/** Data-source abstractions hidden behind the repository. */
interface UserRemoteSource { fun fetch(id: Int): User? }
interface UserLocalSource {
    fun get(id: Int): User?
    fun save(user: User)
}

/** Single source of truth for User data; ViewModels depend on this, never on the sources. */
interface UserRepository {
    fun getUser(id: Int): User?
}

/** Cache-first: local, then remote (and cache the result). Easy to fake in tests. */
class DefaultUserRepository(
    private val local: UserLocalSource,
    private val remote: UserRemoteSource
) : UserRepository {
    override fun getUser(id: Int): User? =
        local.get(id) ?: remote.fetch(id)?.also(local::save)
}
