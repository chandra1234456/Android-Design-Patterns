package com.akshayashokcode.androiddesignpatterns.creational.builder

/**
 * Immutable product built step by step. Required: [url]. Optional: everything else.
 * The constructor is private, so the only way in is [Builder] (or the [httpRequest] DSL).
 */
class HttpRequest private constructor(
    val url: String,
    val method: String,
    val headers: Map<String, String>,
    val body: String?,
    val timeoutMs: Long
) {
    class Builder(private val url: String) {
        private var method = "GET"
        private val headers = mutableMapOf<String, String>()
        private var body: String? = null
        private var timeoutMs = 10_000L

        fun method(value: String) = apply { method = value.uppercase() }
        fun header(key: String, value: String) = apply { headers[key] = value }
        fun body(value: String) = apply { body = value }
        fun timeout(ms: Long) = apply { timeoutMs = ms }

        fun build(): HttpRequest {
            require(url.isNotBlank()) { "url must not be blank" }
            require(timeoutMs > 0) { "timeout must be positive" }
            require(body == null || method in setOf("POST", "PUT", "PATCH")) {
                "$method requests cannot have a body"
            }
            return HttpRequest(url, method, headers.toMap(), body, timeoutMs)
        }
    }
}

/** Kotlin idiom: a type-safe DSL that wraps the Builder. */
fun httpRequest(url: String, block: HttpRequest.Builder.() -> Unit = {}): HttpRequest =
    HttpRequest.Builder(url).apply(block).build()
