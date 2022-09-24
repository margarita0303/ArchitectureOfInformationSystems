package com.architecturecourse.commandlineinterpreter.entities.cmd

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

/* echo - display the entered argument (or arguments) */
class EchoCommand(private val args: List<String>) : Command {
    override val numberOfArgs : Int? = null
    override fun execute(context: VariableContext) : Pair<String, Int> {
        return args.joinToString(separator = " ").trim() to 0
    }
}
