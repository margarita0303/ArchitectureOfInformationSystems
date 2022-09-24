package com.architecturecourse.commandlineinterpreter.entities.cmd

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import java.io.File

/* pwd - print current directory */
class PwdCommand(private val args: List<String>) : Command {
    override val numberOfArgs: Int = 0
    override fun execute(context: VariableContext): Pair<String, Int> {
        return File("").absolutePath to 0
    }
}