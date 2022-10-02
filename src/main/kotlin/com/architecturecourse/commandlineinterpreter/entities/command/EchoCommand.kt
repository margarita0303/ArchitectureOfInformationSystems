package com.architecturecourse.commandlineinterpreter.entities.command

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

/* echo - display the entered argument (or arguments) */
class EchoCommand(private val args: List<String>) : Command {
    override val expectedNumberOfArgs: Int = 0
    override fun execute(context: VariableContext): Pair<String, Int> {
        return args.joinToString(separator = " ").trim() to 0
    }
}
