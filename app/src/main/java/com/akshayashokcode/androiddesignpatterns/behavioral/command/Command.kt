package com.akshayashokcode.androiddesignpatterns.behavioral.command

interface Command {
    fun execute()
    fun undo()
}

class TextEditor { val text = StringBuilder() }

/** Each action is an object, so it can be stored, queued and undone. */
class AppendCommand(private val editor: TextEditor, private val s: String) : Command {
    override fun execute() { editor.text.append(s) }
    override fun undo() { editor.text.setLength(editor.text.length - s.length) }
}

class CommandHistory {
    private val done = ArrayDeque<Command>()

    fun run(command: Command) {
        command.execute()
        done.addLast(command)
    }

    fun undo() { done.removeLastOrNull()?.undo() }
}
