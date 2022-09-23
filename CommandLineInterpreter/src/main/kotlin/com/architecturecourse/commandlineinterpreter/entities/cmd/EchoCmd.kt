package com.architecturecourse.commandlineinterpreter.entities.cmd

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext

/* echo - display the entered argument (or arguments) */
class EchoCmd(override val args: List<String>) : Cmd {
    override val numberOfArgs : Int? = null
    override fun execute(context: VariableContext) : Pair<String, Int> {
        return args.joinToString(separator = " ").trim() to 0
    }
}
