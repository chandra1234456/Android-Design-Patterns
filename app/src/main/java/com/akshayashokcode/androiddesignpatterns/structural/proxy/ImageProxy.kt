package com.akshayashokcode.androiddesignpatterns.structural.proxy

interface ImageLoader { fun load(url: String): String }

/** The real (expensive) object: pretend every call is a network download. */
class RealImageLoader : ImageLoader {
    var downloads = 0
        private set

    override fun load(url: String): String {
        downloads++
        return "bytes-of-$url"
    }
}

/** Proxy: same interface, controls access to the real loader by caching results. */
class CachingImageProxy(private val real: ImageLoader) : ImageLoader {
    private val cache = mutableMapOf<String, String>()
    override fun load(url: String) = cache.getOrPut(url) { real.load(url) }
}
