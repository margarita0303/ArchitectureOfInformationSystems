package com.architecturecourse.commandlineinterpreter.entities.cmd

import com.architecturecourse.commandlineinterpreter.entities.context.VariableContext
import com.architecturecourse.commandlineinterpreter.entities.utils.error.ExitError

/* exit - exit the interpreter */
class ExitCommand(private val args: List<String>) : Command {
    override val numberOfArgs: Int = 0
    override fun execute(context: VariableContext): Pair<String, Int> {
        throw ExitError
    }
}