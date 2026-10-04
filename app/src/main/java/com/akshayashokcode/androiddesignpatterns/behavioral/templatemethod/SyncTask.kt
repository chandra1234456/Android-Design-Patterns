package com.akshayashokcode.androiddesignpatterns.behavioral.templatemethod

/** Template Method: [run] fixes the order of steps; subclasses fill in the steps. */
abstract class SyncTask {
    fun run(): List<String> {
        val log = mutableListOf("start")
        val data = fetch()
        log += "fetched $data"
        save(data)
        log += "saved"
        onFinished()
        log += "done"
        return log
    }

    protected abstract fun fetch(): String
    protected abstract fun save(data: String)
    protected open fun onFinished() {} // optional hook
}

class UserSyncTask : SyncTask() {
    var saved: String? = null
        private set

    override fun fetch() = "users"
    override fun save(data: String) { saved = data }
}
