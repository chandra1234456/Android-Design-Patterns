package com.akshayashokcode.androiddesignpatterns.demo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/** Launcher screen: pick a pattern, see what it does and the real output of running it. */
class PatternListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Android Design Patterns"

        val demos = PatternDemos.all
        val labels = demos.map { "${it.name}   ·   ${it.category}" }

        setContentView(ListView(this).apply {
            adapter = ArrayAdapter(this@PatternListActivity, android.R.layout.simple_list_item_1, labels)
            setOnItemClickListener { _, _, position, _ -> show(demos[position]) }
        })
    }

    private fun show(demo: PatternDemo) {
        val output = runCatching { demo.run() }.getOrElse { "Error: ${it.message}" }
        AlertDialog.Builder(this) // Builder pattern, used by the framework itself
            .setTitle("${demo.name} (${demo.category})")
            .setMessage("When to use:\n${demo.whenToUse}\n\nOutput:\n$output")
            .setPositiveButton("OK", null)
            .show()
    }
}
